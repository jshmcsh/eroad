package jlxy.eroad.server.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.Position;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
import jlxy.eroad.server.bean.param.company.LocationBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import jlxy.eroad.server.core.CompanyDatabase;
import jlxy.eroad.server.core.Corefunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author cp
 */
@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyDatabase cdb;
    // private Corefunc cf;

    public Sret login(LoginBean param) {
        //取用户名、密码，调用访问数据库函数，并接受返回的list
        Sret sr = new Sret();
        String username = param.getUsername();
        String password = param.getPassword();
        List loginRet = cdb.getUserPasswd(username);
        if (loginRet.isEmpty()) {

            sr.setFail("用户不存在");
        }
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
        }
        return sr;
    }
/*
    public Sret regist(LoginBean param) {
        //注册，返回用户uid
        String username = param.getUsername();
        String password = param.getPassword();
        String registRet = cdb.addUser(username, password);
        Sret sr = new Sret();
        sr.setData(registRet);
        return sr;
    }
*/
    public Sret show_car_around(HttpServletRequest req) throws IOException {
        Sret sr = new Sret();
        //用来等会setData()传过去的。
        List<Map<String, Object>> nextRet = new LinkedList();
        double distance;
        //获取ip
        //String ipAddr = ul.getIpAddr(req);
        //解析出地址
        LocationBean lb = Corefunc.getCityNameByIp("112.86.170.43");
        //与数据库数据比对
        List<Map<String, Object>> showCarAroundRet = cdb.showCarAround(lb);
        for(int i=0;i<showCarAroundRet.size();i++){
            distance = Corefunc.Distance(Double.parseDouble(lb.getLongtitude()), Double.parseDouble(lb.getLatitude()), Double.parseDouble((String) showCarAroundRet.get(i).get("longtitude")), Double.parseDouble((String) showCarAroundRet.get(i).get("latitude")));
            if (distance <= 5000) {
                nextRet.add(showCarAroundRet.get(i));
            }
        }
        /*
        Iterator<Map<String, Object>> it = showCarAroundRet.iterator();
        for (; it.hasNext();) {
            Map<String, Object> compareMap = it.next();
            distance = Corefunc.Distance(Double.parseDouble(lb.getLongtitude()), Double.parseDouble(lb.getLatitude()), Double.parseDouble((String) compareMap.get("longtitude")), Double.parseDouble((String) compareMap.get("latitude")));
            System.out.println("distance---》" + distance);
            if (distance <= 5000) {
                nextRet.add(compareMap);
            }
        }
                */
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
