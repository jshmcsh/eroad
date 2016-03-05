package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
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

    // 查询数据库
    public List<Map<String, Object>> getUserList() {
        String stmt = "select * from user";
        
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt);
        return ret;
    }

    public List<Map<String, Object>> getUserList(String username) {
        String stmt = "select * from user where username = ?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{username});
        return ret;
    }

    // 删除记录
    public void removeUser(String username) {
        String stmt = "delete from user where username = ?";
        Root.getInstance().getSqlOperator().delete(stmt, new Object[]{username});
    }

    // 增加用户，返回增加的用户的id
    public String addUser(String username, String passwd) {
        String stmt = "insert into user (username,passwd) values(? , ?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt, new Object[]{username, passwd});
        return ids[0];
    }
    // 插入一条订单到数据库
    public String addSendOrder(OrderBean order){
        String stmt="insert into orders (order_number,start_time,expect_end_time,expect_fare,start_address,destination,description,state) values (?,?,?,?,?,?,?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt, new Object[]{order.getOrder_number(),order.getStart_time(),order.getExpect_end_time(),order.getExpect_fare(),order.getStart_address(),order.getDestination(),order.getDescription(),"发布中"});
        return ids[0];
    }
    //得到所有正在发布的订单
    
    public List getBiddingOrderList(){
        String stmt="select * from orders where state ='displaying'";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt);
        return ret;
    }
    //订单成交
    public String getDeal(SelectBidBean sbb){
        String stmt_update_order="update orders set state = 'executing',finish_or_not='no' where id =?";
        Root.getInstance().getSqlOperator().update(stmt_update_order, new Object[]{sbb.getOrder_id()});
        String stmt_update_carDetail="update car_detail set state = '运输中' where car_id =?";
        Root.getInstance().getSqlOperator().update(stmt_update_carDetail, new Object[]{sbb.getCar_id()});
        String stmt_insert="insert into order_relation_detail (car_id,orders_id,company_id) values(?,?,?)";
        String[] ids=Root.getInstance().getSqlOperator().insert(stmt_insert, new Object[]{sbb.getCar_id(),sbb.getOrder_id(),sbb.getCompany_id()});
        return ids[0];
    }
    //正在运行的订单信息列表
    public List<Map<String, Object>> getExecutingOrderList(IdBean companyId) {
        String stmt = "select * from executing_order_list where id = ?";
        List<Map<String, Object>> ret = Root.getInstance().getSqlOperator().query(stmt, new Object[]{companyId.getId()});
        return ret;
    }
    
    // 修改用户
    public void modifyPasswd(String username, String passwd) {
        String stmt = "update user set passwd = ? where username = ?";
        Root.getInstance().getSqlOperator().update(stmt, new Object[]{passwd, username});

    }
}
