package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
import jlxy.eroad.server.bean.param.company.LocationBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.RegistBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Root;

/**
 * 数据库操作示例
 *
 * @author CP
 */
@Component
public class CompanyDatabase {
    //登录
    public List<Map<String, Object>> getUserPasswd(String username) {
        String stmt = "select id,username,passwd,state,company_name,company_license,phone_number,companylicence_pic_path,company_represent from company where username = ?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{username});
        return ret;
    }

    // 增加用户，返回增加的用户的id
    
    public String regist(RegistBean rb){
        String stmt = "insert into company (username,passwd,company_name,company_license,phone_number,company_licence_path,company_represent) values(?,?,?,?,?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt, new Object[]{rb.getUsername(),rb.getPasswd(),rb.getCompany_name(),rb.getCompany_license(),rb.getPhone_number(),rb.getCompany_licence_path(),rb.getCompany_represent()});
        return ids[0];
    }
    
    //显示周围5公里车辆
    
    public List showCarAround(LocationBean lb) {
        String stmt = "select * from v_show_car_around where province=? and city=?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{lb.getProvince(),lb.getCity()});
        return ret;
    }

    // 发布订单
    public String addSendOrder(OrderBean order) {
        String stmt_insert_orders = "insert into orders (order_number,start_time,expect_end_time,expect_fare,start_address,destination,description,sketch,state) values (?,?,?,?,?,?,?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt_insert_orders, new Object[]{order.getOrder_number(), order.getStart_time(), order.getExpect_end_time(), order.getExpect_fare(), order.getStart_address(), order.getDestination(), order.getDescription(),order.getSketch(), "displaying"});
        String stmt_insert_launching="insert into launching (flag,company_id,order_id) values (?,?,?)";
        String[] ids2 = Root.getInstance().getSqlOperator().insert(stmt_insert_launching, new Object[]{"valid",order.getCompany_id(),ids[0]});
       
        return ids2[0];
    }

    //得到所有正在发布的订单
    public List getBiddingOrderList(IdBean companyId) {
        String stmt = "select * from bidding_order_list where id=?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{companyId.getId()});
        return ret;
    }

    //订单成交

    public String getDeal(SelectBidBean sbb) {
        String stmt_update_order = "update orders set state = 'executing',exact_fare=? where id =?";
        Root.getInstance().getSqlOperator().update(stmt_update_order, new Object[]{sbb.getPrice(),sbb.getOrder_id()});
        String stmt_update_car_detail = "update car_detail set state = '运输中' where car_id =?";
        Root.getInstance().getSqlOperator().update(stmt_update_car_detail, new Object[]{sbb.getCar_id()});
        String stmt_update_launching = "update launching set flag = 'invalid' where order_id =?";
        Root.getInstance().getSqlOperator().update(stmt_update_launching, new Object[]{sbb.getOrder_id()});
        String stmt_insert = "insert into order_relation_detail (car_id,orders_id,company_id) values(?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt_insert, new Object[]{sbb.getCar_id(), sbb.getOrder_id(), sbb.getCompany_id()});
        return ids[0];
    }

    //正在运行的订单信息列表

    public List<Map<String, Object>> getExecutingOrderList(IdBean companyId) {
        
        String stmt = "select * from executing_order_list where id=? group by order_id ";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{companyId.getId()});
        return ret;
    }
    //显示正在运行订单的详细信息
    public List<Map<String, Object>> getExecutingOrderDetail(IdBean orderId) {
        String stmt = "select * from executing_order_detail where order_id = ?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{orderId.getId()});
        return ret;
    }
    

    // 结束订单

    public String finishOrder(FinishOrderBean fob) {
        String stmt_update_orders = "update orders set state='completed',exact_end_time=?,exact_start_time=? where id=?";
        Root.getInstance().getSqlOperator().update(stmt_update_orders, new Object[]{fob.getExact_end_time(),fob.getExact_start_time(),fob.getOrder_id()});
        String stmt_insert_remark = "insert into remark (evaluate,remark,car_id,order_id,company_id) values(?,?,?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt_insert_remark, new Object[]{fob.getEvaluate(),fob.getRemark(),fob.getCar_id(),fob.getOrder_id(),"1"});
        return ids[0];
    }
    
    // 取消发布中的订单

    public void cancelLaunchingOrder(CancelLaunchingOrderBean clob) {
        String stmt_update_orders = "update orders set state = 'invalid' where id=?";
        Root.getInstance().getSqlOperator().update(stmt_update_orders, new Object[]{clob.getOrder_id()});
        String stmt_update_launching = "update launching set flag = 'invalid' where id=?";
        Root.getInstance().getSqlOperator().update(stmt_update_launching, new Object[]{clob.getOrder_id()});
    }
    // 取消运输中的订单

    public String cancelExecutingOrder(CancelExecutingOrderBean ceob) {
        String stmt_update_orders = "update orders set state = 'invalid' where id=?";
        Root.getInstance().getSqlOperator().update(stmt_update_orders, new Object[]{ceob.getOrder_id()});
        String stmt_update_launching = "update car_detail set state = '空闲' where car_id=?";
        Root.getInstance().getSqlOperator().update(stmt_update_launching, new Object[]{ceob.getCar_id()});
        String stmt_insert_remark = "insert into remark (evaluate,car_id,order_id,company_id) values(?,?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt_insert_remark, new Object[]{ceob.getEvaluate(),ceob.getCar_id(),ceob.getOrder_id(),"1"});
       return ids[0];
    }
    
    // 得到历史订单
    public List getHistoryOrderList(IdBean companyId) {
       String stmt_select_view = "select order_number,last_time,start_address,destination,sketch,username,exact_fare from history_order where company_id=?";
       List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt_select_view, new Object[]{companyId.getId()});
       return ret;
    }
    // 得到历史订单详情
    public List getHistoryOrderDetail(HistoryOrderDetailBean hodb) {
       String stmt_select_view = "select * from history_order where order_number=?";
       List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt_select_view, new Object[]{hodb.getOrder_number()});
       return ret;
    }
    // 得到某车的评价
    public List getCarRemark(IdBean carId) {
       String stmt_select_view = "select * from xcar_remark where id=?";
       List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt_select_view, new Object[]{carId.getId()});
       return ret;
    }
        
   
}
