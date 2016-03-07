package jlxy.eroad.server.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.yecq.baseframework.test.IntegrateBase;
import org.yecq.baseframework.web.Head;
import org.yecq.baseframework.web.MakeLogged;

/**
 *
 * @author yecq
 */
public class CompanyControllerTest extends IntegrateBase {
    /*
     @Test
     public void test_do_login() {
     LoginBean param = new LoginBean("abcd", "123456");
     List list = getJsonReturn("/company/login.erd", param);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));
      
     LoginBean param2 = new LoginBean("abcd", "12345");
     List list2 = getJsonReturn("/company/login.erd", param2);
     Head head2 = getHeader(list2);
     assertThat(head2.getStatus(), is("fail"));
        
     LoginBean param3 = new LoginBean("aaa", "aaa");
     List list3 = getJsonReturn("/company/login.erd", param3);
     Head head3 = getHeader(list3);
     assertThat(head3.getStatus(), is("fail"));
                
     }
   
     @Test
     public void test_do_regist() {
        
     LoginBean param = new LoginBean("chris", "aaa");
     List list = getJsonReturn("/company/regist.erd", param);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));
     int addid=Integer.parseInt(getSingleObject(list, String.class));
     assertThat(addid, is(7));
        
     }
    
    
     @Test
     public void test_do_logout() {
  
     List list = getJsonReturn("/company/logout.erd");
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok")); 
        
     }
     @Test
     public void test_show_car_around() {
  
     List list = getJsonReturn("/company/show_car_around.erd");
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok")); 
        
     }*/
    /*
     @Test
     public void test_throwException() {
     List list = getJsonReturn("/test/throw_exception.erd");
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("fail"));
     assertThat(head.getMessage(), is("错误的状态")
     );

     }
     */
    /*
     //发布订单
     @Test
     public void test_do_send_order() {

     OrderBean param = new OrderBean("444", "2012-1-1", "2012-1-1", "111", "撒范德萨", "北京", "上海","撒范",1);
     List list = getJsonReturn("/company/send_order.erd", param);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));

     int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
     assertThat(addid, is(1));

     }
     */
    /*
     //得到正在发布的订单列表
     @Test
     public void test_do_get_bidding_order_list() {
     IdBean companyId=new IdBean("1");
     List list = getJsonReturn("/company/get_bidding_order_list.erd",companyId);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));

     List orderlist = getListObject(list, Map.class);
     //如果是多个数据就是getlistObject()方法
       
     Iterator<Map<String, String>> it = orderlist.iterator();
     for (; it.hasNext();) {
     Map<String, String> map2 = it.next();
     assertThat((String) map2.get("order_number"), is("111"));
     assertThat((String) map2.get("start_time"), is("2012-01-01"));
     assertThat((String) map2.get("expect_end_time"), is("2012-02-01"));
     //assertThat(Double.parseDouble(map2.get("expect_fare"))+"", is("111"));//不知为何，这里的get方法返回的都要默认转成string类型，而这个字段在数据库是double类型，所以他报错无法转成string
     assertThat((String) map2.get("description"), is("来自北方的车"));
     assertThat((String) map2.get("start_address"), is("北京"));
     assertThat((String) map2.get("destination"), is("上海"));

     }

     }
     */

    /*
     //订单成交
     @Test
     public void test_do_deal_order() {

     SelectBidBean sbb = new SelectBidBean("1", "1", "1", "111");
     List list = getJsonReturn("/company/deal_order.erd", sbb);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));
        
     int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
     assertThat(addid, is(1));

     }
     */
    /*
     //得到正在运行的订单的列表
     @Test
     public void test_do_get_executing_order() {

     IdBean companyId = new IdBean("1");
     List list = getJsonReturn("/company/get_executing_order.erd", companyId);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));

     List executingOrderList = getListObject(list, Map.class);
     //如果是多个数据就是getlistObject()方法

     Iterator<Map<String, String>> it = executingOrderList.iterator();
     for (; it.hasNext();) {
     Map<String, String> map2 = it.next();
     //assertThat((String) map2.get("id") , is("1"));
     assertThat((String) map2.get("username"), is("aaa"));
     assertThat((String) map2.get("car_number"), is("苏E12345"));
     assertThat((String) map2.get("phone_number"), is("11111111111"));
     assertThat((String) map2.get("sketch"), is("车"));
     assertThat((String) map2.get("start_address"), is("北京"));
     assertThat((String) map2.get("destination"), is("上海"));

     }
     }
     */
    /*
     //结束订单
     @Test
     public void test_do_finish_order() {

     FinishOrderBean fob = new FinishOrderBean("1", "1", 5, "非常好");
     List list = getJsonReturn("/company/finish_order.erd", fob);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));
        
     int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
     assertThat(addid, is(1));

     }
     */
    /*
    //取消发布中的订单
    @Test
    public void test_do_cancel_launching_order() {

        CancelLaunchingOrderBean clob = new CancelLaunchingOrderBean(1, 1);
        List list = getJsonReturn("/company/cancel_launching_order.erd", clob);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

    }
      
*/
    /*
    //取消运行中的订单
    @Test
    public void test_do_cancel_executing_order() {

        CancelExecutingOrderBean clob = new CancelExecutingOrderBean("1", "1", "太慢", 2);
        List list = getJsonReturn("/company/cancel_executing_order.erd", clob);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
        assertThat(addid, is(21));
    }
    */
    
    //获得司机的评价
     @Test
    public void test_do_get_car_remark() {

        IdBean carId=new IdBean("1");
        List list = getJsonReturn("/company/get_car_remark.erd", carId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));
        
        assertThat(((Map)list.get(1)).get("evaluate")+"", is("2"));
        assertThat(((Map)list.get(1)).get("remark")+"", is("good"));
        assertThat(((Map)list.get(1)).get("username")+"", is("aaa"));
        assertThat(((Map)list.get(1)).get("car_number")+"", is("苏E12345"));
        assertThat(((Map)list.get(1)).get("company_name")+"", is("e路网"));
    }
    
}
