package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.company.OrderBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Root;

/**
 * 数据库操作示例
 *
 * @author cp
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
        String stmt="insert into orders (order_number,start_time,expect_end_time,expect_fare,start_address,destination,description,state) values (?,?)";
        String[] ids = Root.getInstance().getSqlOperator().insert(stmt, new Object[]{order.getOrder_number(),order.getStart_time(),order.getExpect_end_time(),order.getExpect_fare(),order.getStart_address(),order.getDestination(),order.getDescription(),"发布中"});
        return ids[0];
    }
    // 修改用户
    public void modifyPasswd(String username, String passwd) {
        String stmt = "update user set passwd = ? where username = ?";
        Root.getInstance().getSqlOperator().update(stmt, new Object[]{passwd, username});
    }
}
