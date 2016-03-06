package jlxy.eroad.server.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.Position;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import jlxy.eroad.server.core.CompanyDatabase;
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

    public Sret login(LoginBean param) {
        //取用户名、密码，调用访问数据库函数，并接受返回的list
        Sret sr = new Sret();
        String username = param.getUsername();
        String password = param.getPassword();
        List loginRet = cdb.getUserList(username);
        if (loginRet.isEmpty()) {
            sr.setFail("用户不存在");
        }
        //迭代，比对信息

        Iterator<Map<String, Object>> it = loginRet.iterator();
        for (; it.hasNext();) {
            Map<String, Object> compareMap = it.next();
            if (compareMap.get("passwd") != null) {

                if (compareMap.get("passwd").equals(password)) {
                    // sr.setOk("登录成功");
                    System.out.println("登录成功");

                } else {
                    sr.setFail("密码错误");

                }
            }
        }
        return sr;
    }

    public Sret regist(LoginBean param) {
        //注册，返回用户uid
        String username = param.getUsername();
        String password = param.getPassword();
        String registRet = cdb.addUser(username, password);
        Sret sr = new Sret();
        sr.setData(registRet);
        return sr;
    }

    public Sret ShowCarAround(Position param) {
        int longtitude=param.getLongitude();
        int latitude=param.getLatitude();
        //数据库查询实时车辆经纬
        //循环比对
        Sret sr = new Sret();
        sr.setOk();
        return sr;
    }
    
    public Sret send_order(OrderBean order){
        String addSendOrderRet=cdb.addSendOrder(order);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(addSendOrderRet);
        return sr;
    }
    
    public Sret get_bidding_order_list(IdBean companyId){
        List BiddingOrderList=cdb.getBiddingOrderList(companyId);
        Sret sr=new Sret();
        sr.setOk();
        sr.setData(BiddingOrderList);
        return sr;
    }
    
    //订单成交
    public Sret deal_order(SelectBidBean orderbean){
        String dealOrderRet=cdb.getDeal(orderbean);
        Sret sr=new Sret();
        sr.setOk();
        sr.setData(dealOrderRet);
        return sr;
    }
    //正在运行的订单信息列表
    public Sret get_executing_order(IdBean companyId){
        List ExecutingOrderListRet=cdb.getExecutingOrderList(companyId);
        Sret sr=new Sret();
        sr.setOk();
        sr.setData(ExecutingOrderListRet);
        return sr;
    }
    //结束订单
    public Sret finish_order(FinishOrderBean fobean){
        String finishOrderRet=cdb.finishOrder(fobean);
        Sret sr=new Sret();
        sr.setOk();
        sr.setData(finishOrderRet);
        return sr;
    }
    //结束订单
    public Sret cancel_launching_order(CancelLaunchingOrderBean clob){
        cdb.cancelLaunchingOrder(clob);
        Sret sr=new Sret();
        sr.setOk();
        return sr;
    }
    
    public Sret throwException() {
        throw new IllegalStateException("错误的状态");
    }
    
    
    
}
