package jlxy.eroad.server.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.Position;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.CompanyBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
import jlxy.eroad.server.bean.param.company.LocationBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.RegistBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.core.CompanyDatabase;
import jlxy.eroad.server.core.Corefunc;
import jlxy.eroad.server.core.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author cp
 */
@Service
@Transactional
public class CompanyService {

    private HttpServletRequest request;
    private CompanyBean cb;
    private Corefunc cf;
    @Autowired
    private CompanyDatabase cdb;

    // private Corefunc cf;
    public Sret login(LoginBean param, HttpServletRequest req,  HttpServletResponse resp) {
        //取用户名、密码，
        Sret sr = new Sret();
        String username = param.getUsername();
        String password = param.getPassword();
        //密码加密
        password = Md5.convertToMD5(password);

        //调用访问数据库函数，并接受返回的list
        List<Map<String, Object>> loginRet = cdb.getUserPasswd(username);
        if (loginRet.isEmpty()) {
            sr.setFail("用户不存在");
        } else if (loginRet.get(0).get("passwd").equals(password)) {
            sr.setOk("登录成功");
            cb = new CompanyBean(loginRet.get(0).get("id") + "", loginRet.get(0).get("username") + "", loginRet.get(0).get("state") + "", loginRet.get(0).get("company_name") + "", loginRet.get(0).get("company_license") + "", loginRet.get(0).get("phone_number") + "", loginRet.get(0).get("companylicence_pic_path") + "", loginRet.get(0).get("company_represent") + "");

            //保存用户登录状态
            int TIME_OUT=20*60;
            HttpSession session = req.getSession();
            session.setAttribute("companyInfo", cb);
            session.setMaxInactiveInterval(TIME_OUT);  // Session保存两小时
            System.out.println("sessionid---->"+session.getId());
            Cookie cookie = new Cookie("MYSESSIONID", session.getId());
            cookie.setMaxAge(TIME_OUT);  // 客户端的JSESSIONID也保存两小时
            cookie.setPath("/");
            resp.addCookie(cookie);

        } else {
            sr.setFail("密码错误");
        }
        /*
         //迭代，比对信息
         Iterator<Map<String, Object>> it = loginRet.iterator();
         for (; it.hasNext();) {
         Map<String, Object> compareMap = it.next();
         if (compareMap.get("passwd") != null) {

         if (compareMap.get("passwd").equals(password)) {
         sr.setOk("登录成功");
         // System.out.println("登录成功");
                    
         } else {
         sr.setFail("密码错误");

         }
         }
         */
        return sr;
    }

    public Sret regist(RegistBean rb, MultipartFile file) {
        String filePath = "";
        if (!file.isEmpty()) {
            try {
                // 文件保存路径  
                filePath = request.getSession().getServletContext().getRealPath("/") + "pic/"
                        + file.getOriginalFilename();
                // 转存文件  
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        rb.setCompany_licence_path(filePath);
        String registRet = cdb.regist(rb);
        Sret sr = new Sret();
        sr.setData(registRet);
        return sr;
    }

    public Sret show_car_around(HttpServletRequest req) throws IOException {
        Sret sr = new Sret();
        //用来等会setData()传过去的。
        List<Map<String, Object>> nextRet = new LinkedList();
        double distance;
        //获取ip
        //String ipAddr = cf.getIpAddr(req);
        //解析出地址
        LocationBean lb = Corefunc.getCityNameByIp("112.86.170.43");
        //与数据库数据比对
        List<Map<String, Object>> showCarAroundRet = cdb.showCarAround(lb);
        for (int i = 0; i < showCarAroundRet.size(); i++) {
            distance = Corefunc.Distance(Double.parseDouble(lb.getLongtitude()), 
                    Double.parseDouble(lb.getLatitude()), Double.parseDouble((String) 
                    showCarAroundRet.get(i).get("longtitude")), Double.parseDouble((String) 
                            showCarAroundRet.get(i).get("latitude")));
            if (distance <= 5000) {
                nextRet.add(showCarAroundRet.get(i));
            }
        }
        //返回符合的经纬度
        sr.setOk();
        sr.setData(nextRet);
        return sr;
    }

    public Sret send_order(OrderBean order) {
        String addSendOrderRet = cdb.addSendOrder(order);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(addSendOrderRet);
        return sr;
    }

    public Sret get_bidding_order_list(IdBean companyId) {
        List BiddingOrderList = cdb.getBiddingOrderList(companyId);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(BiddingOrderList);
        return sr;
    }

    //订单成交
    public Sret deal_order(SelectBidBean orderbean) {
        String dealOrderRet = cdb.getDeal(orderbean);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(dealOrderRet);
        return sr;
    }

    //正在运行的订单信息列表
    public Sret get_executing_order(IdBean companyId) {
        List ExecutingOrderListRet = cdb.getExecutingOrderList(companyId);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ExecutingOrderListRet);
        return sr;
    }

    //正在运行的订单详细信息
    public Sret get_executing_order_detail(IdBean orderId) {
        List ExecutingOrderListRet = cdb.getExecutingOrderDetail(orderId);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ExecutingOrderListRet);
        return sr;
    }

    //结束订单
    public Sret finish_order(FinishOrderBean fobean) {
        String finishOrderRet = cdb.finishOrder(fobean);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(finishOrderRet);
        return sr;
    }

    //取消正在发布的订单
    public Sret cancel_launching_order(CancelLaunchingOrderBean clob) {
        cdb.cancelLaunchingOrder(clob);
        Sret sr = new Sret();
        sr.setOk();
        return sr;
    }

    //取消正在运行的订单
    public Sret cancel_executing_order(CancelExecutingOrderBean ceob) {
        String cancelExecutingOrderRet = cdb.cancelExecutingOrder(ceob);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(cancelExecutingOrderRet);
        return sr;
    }

    //得到司机评价
    public Sret get_car_remark(IdBean carId) {
        List ret = cdb.getCarRemark(carId);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }

    //得到历史订单列表
    public Sret get_history_order_list(IdBean companyId) {
        List ret = cdb.getHistoryOrderList(companyId);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }

    //得到历史订单详情
    public Sret get_history_order_detail(HistoryOrderDetailBean hodb) {
        List ret = cdb.getHistoryOrderDetail(hodb);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }

    public Sret throwException() {
        throw new IllegalStateException("错误的状态");
    }

}
