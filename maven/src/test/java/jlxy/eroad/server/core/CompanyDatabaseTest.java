package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
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
public class CompanyDatabaseTest extends Base {

    @Autowired
    private CompanyDatabase cdb;

    @Autowired
    private SqlOperator sql;
    /*
     @Test
     public void test_getUserList() {
     List<Map<String, Object>> list = cdb.getUserList();
     assertThat(list.size(), is(2));
     assertThat(list.get(0).get("username") + "", is("abcd"));
     assertThat(list.get(1).get("username") + "", is("zxcv"));
     }
     */
    /*
     @Test
     public void test1_getUserList() {
     List<Map<String, Object>> list = cdb.getUserList("abcd");
     assertThat(list.size(), is(1));
     assertThat(list.get(0).get("username") + "", is("abcd"));
     }
     */
    /*
     @Test
     public void test_removeUser() {
     cdb.removeUser("abcd");
     List list = sql.query("select * from user where username='abcd'");
     assertThat(list.size(), is(0));
     }
     */
    /*
     @Test
     public void test_addUser() {
     cdb.addUser("qwer", "999999");
     List list = sql.query("select * from user where username=? and passwd=?", new Object[]{"qwer", "999999"});
     assertThat(list.size(), is(1));
     }
     */
    //发布一条订单

    @Test
    public void test_addSendOrder() {
        OrderBean param = new OrderBean("444", "2012-1-1", "2012-1-1", "111", "撒范德萨", "北京", "上海", "撒范", 1);
        cdb.addSendOrder(param);
        List<Map<String, Object>> list = sql.query("select * from orders");
        List<Map<String, Object>> list2 = sql.query("select * from launching");
        assertThat(list.size(), is(4));
        //assertThat(list.get(3).get("id") + "", is("15"));
        assertThat(list.get(3).get("order_number") + "", is("444"));
        assertThat(list.get(3).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(3).get("expect_end_time") + "", is("2012-01-01"));
        assertThat(list.get(3).get("expect_fare") + "", is("111.00"));
        assertThat(list.get(3).get("description") + "", is("撒范德萨"));
        assertThat(list.get(3).get("start_address") + "", is("北京"));
        assertThat(list.get(3).get("sketch") + "", is("撒范"));
        
        assertThat(list2.size(), is(2));
       // assertThat(list2.get(1).get("id") + "", is("3"));
        assertThat(list2.get(1).get("flag") + "", is("valid"));
        assertThat(list2.get(1).get("company_id") + "", is("1"));
        assertThat(list2.get(1).get("order_id") + "", is(list.get(3).get("id")+""));

    }

    /*
     @Test
     public void test_finishOrder() {
     FinishOrderBean fob = new FinishOrderBean("1", "1", 2, "非常好");
     cdb.finishOrder(fob);
     List<Map<String, Object>> list = sql.query("select finish_or_not from orders where id=? ", new Object[]{1});
     List<Map<String, Object>> list2 = sql.query("select * from remark");
     assertThat(list.size(), is(1));
     assertThat(list.get(0).get("finish_or_not") + "", is("yes"));
     assertThat(list2.size(), is(1));
     System.out.println("list2.size--->"+list2.size());
     assertThat(list2.get(0).get("id") + "", is("17"));
     assertThat(list2.get(0).get("evaluate") + "", is("2"));
     assertThat(list2.get(0).get("remark") + "", is("非常好"));
     assertThat(list2.get(0).get("car_id") + "", is("1"));
     assertThat(list2.get(0).get("order_id") + "", is("1"));
     }
     */
    /*
     @Test
     public void test_modifyPasswd() {
     cdb.modifyPasswd("zxcv", "000000");
     List<Map<String, Object>> list = sql.query("select passwd from user where username=? ", new Object[]{"zxcv"});
     assertThat(list.size(), is(1));
     assertThat(list.get(0).get("passwd") + "", is("000000"));
     }
     */
}
