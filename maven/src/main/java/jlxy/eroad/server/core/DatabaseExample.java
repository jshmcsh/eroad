package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Root;

/**
 * 数据库操作示例
 *
 * @author yecq
 */
@Component
public class DatabaseExample {

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

    // 修改用户
    public void modifyPasswd(String username, String passwd) {
        String stmt = "update user set passwd = ? where username = ?";
        Root.getInstance().getSqlOperator().update(stmt, new Object[]{passwd, username});
    }
}
