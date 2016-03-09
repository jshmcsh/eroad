package jlxy.eroad.server.core;

import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.test.Base;
import org.yecq.baseframework.web.Head;
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
     */
    /*
    //得到所有正在发布的订单
    @Test
    public void test_getBiddingOrderList() {
        IdBean companyId = new IdBean("1");
        List<Map<String, Object>> list = cdb.getBiddingOrderList(companyId);

        assertThat(list.size(), is(1));
        assertThat(list.get(0).get("company_id") + "", is("1"));
        assertThat(list.get(0).get("destination") + "", is("上海"));
        assertThat(list.get(0).get("start_address") + "", is("北京"));
        assertThat(list.get(0).get("expect_fare") + "", is("111.00"));
        assertThat(list.get(0).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(0).get("expect_end_time") + "", is("2012-02-01"));

        assertThat(list.get(0).get("sketch") + "", is("车"));
        assertThat(list.get(0).get("price") + "", is("111"));
        assertThat(list.get(0).get("username") + "", is("aaa"));
        assertThat(list.get(0).get("phone_numer") + "", is("11111111111"));
        assertThat(list.get(0).get("car_numer") + "", is("苏E12345"));

    }
*/
    /*
     //订单成交
     @Test
     public void test_getDeal() {
     SelectBidBean sbb = new SelectBidBean("1", "1", "1", "111");
     String strRet = cdb.getDeal(sbb);

     List<Map<String, Object>> list = sql.query("select state,exact_fare from orders where id =?",new Object[]{1});
     List<Map<String, Object>> list2 = sql.query("select state from car_detail where car_id =?",new Object[]{1});
     List<Map<String, Object>> list3 = sql.query("select flag from launching where order_id =?",new Object[]{1});
     List<Map<String, Object>> list4 = sql.query("select car_id,orders_id,company_id from order_relation_detail where orders_id =?",new Object[]{1});
     assertThat(list.get(0).get("order_number") + "", is("111"));
     assertThat(list.get(0).get("start_time") + "", is("2012-01-01"));
     assertThat(list.get(0).get("expect_end_time") + "", is("2012-02-01"));
     assertThat(list.get(0).get("expect_fare") + "", is("111.00"));
     assertThat(list.get(0).get("description") + "", is("来自北方的车"));
     assertThat(list.get(0).get("start_address") + "", is("北京"));
     assertThat(list.get(0).get("destination") + "", is("上海"));

     }
     */
    /*
     //显示正在运行的订单信息列表
     @Test
     public void test_getExecutingOrderList() {
     IdBean companyId = new IdBean("1");
     List<Map<String, Object>> list = cdb.getExecutingOrderList(companyId);
        
     assertThat(list.get(0).get("id") + "", is("1"));
     assertThat(list.get(0).get("username") + "", is("aaa"));
     assertThat(list.get(0).get("car_number") + "", is("苏E12345"));
     assertThat(list.get(0).get("phone_number") + "", is("11111111111"));
     assertThat(list.get(0).get("sketch") + "", is("猪"));
     assertThat(list.get(0).get("start_address") + "", is("广州"));
     assertThat(list.get(0).get("destination") + "", is("厦门"));
     }
     */
   /*
     //结束一条订单
     @Test
     public void test_finishOrder() {
     FinishOrderBean fob = new FinishOrderBean("1", "1", 2, "非常好","2012-02-03", "2012-01-03");
     cdb.finishOrder(fob);
     List<Map<String, Object>> list = sql.query("select state,exact_end_time,exact_start_time from orders where id=? ", new Object[]{1});
     List<Map<String, Object>> list2 = sql.query("select * from remark");
     assertThat(list.size(), is(1));
     assertThat(list.get(0).get("state") + "", is("completed"));
     assertThat(list.get(0).get("exact_end_time") + "", is("2012-02-03"));
     assertThat(list.get(0).get("exact_start_time") + "", is("2012-01-03"));
     
     assertThat(list2.size(), is(2));
     System.out.println("list2.size--->"+list2.size());
     // assertThat(list2.get(0).get("id") + "", is("17"));
     assertThat(list2.get(1).get("evaluate") + "", is("2"));
     assertThat(list2.get(1).get("remark") + "", is("非常好"));
     assertThat(list2.get(1).get("car_id") + "", is("1"));
     assertThat(list2.get(1).get("order_id") + "", is("1"));
     }
     */
    /*
     //取消正在发布的订单
     @Test
     public void test_cancelLaunchingOrder() {
     CancelLaunchingOrderBean clob = new CancelLaunchingOrderBean(1, 1);
     cdb.cancelLaunchingOrder(clob);
     List<Map<String, Object>> list1 = sql.query("select state from orders where id=? ", new Object[]{1});
     List<Map<String, Object>> list2 = sql.query("select flag from launching where order_id=?" , new Object[]{1});
     assertThat(list1.size(), is(1));
     assertThat(list1.get(0).get("state") + "", is("invalid"));
     assertThat(list2.size(), is(1));
     assertThat(list2.get(0).get("flag") + "", is("invalid"));
     }
     */
    /*
     //取消运输中的订单
     @Test
     public void test_modifyPasswd() {
     CancelExecutingOrderBean clob = new CancelExecutingOrderBean("1", "1", "太慢", 2);
     String ret=cdb.cancelExecutingOrder(clob);
     
     List<Map<String, Object>> list1 = sql.query("select state from orders where id=? ", new Object[]{"1"});
     List<Map<String, Object>> list2= sql.query("select state from car_detail where car_id=? ", new Object[]{"1"});
     List<Map<String, Object>> list3= sql.query("select * from remark");
     
     assertThat(list1.get(0).get("state")+"", is("invalid"));
     assertThat(list2.get(0).get("state")+"", is("空闲"));
     
     assertThat(list3.size(), is(2));
     assertThat(list3.get(1).get("evaluate") + "", is("2"));
     assertThat(list3.get(1).get("remark") + "", is("null"));
     assertThat(list3.get(1).get("car_id") + "", is("1"));
     assertThat(list3.get(1).get("order_id") + "", is("1"));
     assertThat(list3.get(1).get("company_id") + "", is("1"));
     }
     */
    /*
     //得到历史订单列表
     @Test
     public void test_getHistoryOrderList() {
     IdBean companyId = new IdBean("1");
     List<Map<String, Object>> list = cdb.getHistoryOrderList(companyId);
     assertThat(list.size(), is(1));
     assertThat((list.get(0)).get("order_number") + "", is("111"));
     assertThat((list.get(0)).get("last_time") + "", is("31"));
     assertThat((list.get(0)).get("start_address") + "", is("北京"));
     assertThat((list.get(0)).get("destination") + "", is("上海"));
     assertThat((list.get(0)).get("sketch") + "", is("车"));
     assertThat((list.get(0)).get("username") + "", is("aaa"));
     assertThat((list.get(0)).get("exact_fare") + "", is("111.00"));
     }
     */
    /*
     //得到历史订单详情
     @Test
     public void test_getHistoryOrderList() {
     HistoryOrderDetailBean hodb = new HistoryOrderDetailBean("111");
     List<Map<String, Object>> list = cdb.getHistoryOrderDetail(hodb);

     assertThat(((Map) list.get(0)).size() + "", is("14"));
     assertThat(((Map) list.get(0)).get("company_id") + "", is("1"));
     assertThat(((Map) list.get(0)).get("order_number") + "", is("111"));
     assertThat(((Map) list.get(0)).get("last_time") + "", is("31"));
     assertThat(((Map) list.get(0)).get("exact_fare") + "", is("111.00"));
     assertThat(((Map) list.get(0)).get("expect_fare") + "", is("111.00"));
     assertThat(((Map) list.get(0)).get("expect_end_time") + "", is("2012-02-01"));
     assertThat(((Map) list.get(0)).get("exact_end_time") + "", is("2012-02-01"));
     assertThat(((Map) list.get(0)).get("start_time") + "", is("2012-01-01"));
     assertThat(((Map) list.get(0)).get("start_address") + "", is("北京"));
     assertThat(((Map) list.get(0)).get("destination") + "", is("上海"));
     assertThat(((Map) list.get(0)).get("sketch") + "", is("车"));
     assertThat(((Map) list.get(0)).get("description") + "", is("来自北方的车"));
     assertThat(((Map) list.get(0)).get("username") + "", is("aaa"));
     }
     */
    /*
     //获得司机的评价
     @Test
     public void testgetCarRemark() {

     IdBean carId=new IdBean("1");
     List list=cdb.getCarRemark(carId);
        
     assertThat(((Map)list.get(0)).get("evaluate")+"", is("2"));
     assertThat(((Map)list.get(0)).get("remark")+"", is("good"));
     assertThat(((Map)list.get(0)).get("username")+"", is("aaa"));
     assertThat(((Map)list.get(0)).get("car_number")+"", is("苏E12345"));
     assertThat(((Map)list.get(0)).get("company_name")+"", is("e路网"));
     }
     */
}
