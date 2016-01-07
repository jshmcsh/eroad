package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.test.Base;
import org.yecq.record.SqlOperator;

/**
 *
 * @author yecq
 */
public class DatabaseExampleTest extends Base {

    @Autowired
    private DatabaseExample db;

    @Autowired
    private SqlOperator sql;

    @Test
    public void test_getUserList() {
        List<Map<String, Object>> list = db.getUserList();
        assertThat(list.size(), is(2));
        assertThat(list.get(0).get("username") + "", is("abcd"));
        assertThat(list.get(1).get("username") + "", is("zxcv"));
    }

    @Test
    public void test1_getUserList() {
        List<Map<String, Object>> list = db.getUserList("abcd");
        assertThat(list.size(), is(1));
        assertThat(list.get(0).get("username") + "", is("abcd"));
    }

    @Test
    public void test_removeUser() {
        db.removeUser("abcd");
        List list = sql.query("select * from user where username='abcd'");
        assertThat(list.size(), is(0));
    }

    @Test
    public void test_addUser() {
        db.addUser("qwer", "999999");
        List list = sql.query("select * from user where username=? and passwd=?", new Object[]{"qwer", "999999"});
        assertThat(list.size(), is(1));
    }

    @Test
    public void test_modifyPasswd() {
        db.modifyPasswd("zxcv", "000000");
        List<Map<String, Object>> list = sql.query("select passwd from user where username=? ", new Object[]{"zxcv"});
        assertThat(list.size(), is(1));
        assertThat(list.get(0).get("passwd") + "", is("000000"));
    }
}
