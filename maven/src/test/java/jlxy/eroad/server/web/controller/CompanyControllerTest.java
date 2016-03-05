package jlxy.eroad.server.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.yecq.baseframework.test.IntegrateBase;
import org.yecq.baseframework.web.Head;

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
     @Test
     public void test_do_send_order() {

     OrderBean param = new OrderBean("111", "2012-1-1", "2012-1-1", "111", "撒范德萨", "北京", "上海");
     List list = getJsonReturn("/company/send_order.erd", param);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));

     int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
     assertThat(addid, is(1));

     }
     */
    /*
     @Test
     public void test_do_get_bidding_order_list() {
     List list = getJsonReturn("/company/get_bidding_order_list.erd");
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
     assertThat((String) map2.get("description"), is("车"));
     assertThat((String) map2.get("start_address"), is("北京"));
     assertThat((String) map2.get("destination"), is("上海"));

     }

     }
     */

    @Test
    public void test_do_deal_order() {

        SelectBidBean sbb = new SelectBidBean("1", "1", "1", "111");
        List list = getJsonReturn("/company/deal_order.erd", sbb);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));
        
        int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
        assertThat(addid, is(1));

    }
    
}
