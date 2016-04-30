package jlxy.eroad.server.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
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

    //登录
    @Test
    public void test_do_login() {
        LoginBean param = new LoginBean("aaa", "fdsa");
        List list = getJsonReturn("/company/login.erd", param);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));
        assertThat(head.getMessage(), is("登录成功"));

        LoginBean param2 = new LoginBean("aaa", "fds");
        List list2 = getJsonReturn("/company/login.erd", param2);
        Head head2 = getHeader(list2);
        assertThat(head2.getStatus(), is("fail"));
        assertThat(head2.getMessage(), is("密码错误"));

        LoginBean param3 = new LoginBean("ccc", "aaa");
        List list3 = getJsonReturn("/company/login.erd", param3);
        Head head3 = getHeader(list3);
        assertThat(head3.getStatus(), is("fail"));
        assertThat(head3.getMessage(), is("用户不存在"));

    }

    /*
     //注册
     @Test
     public void test_do_regist() {
        
     LoginBean param = new LoginBean("chris", "aaa");
     List list = getJsonReturn("/company/regist.erd", param);
     Head head = getHeader(list);
     assertThat(head.getStatus(), is("ok"));
     int addid=Integer.parseInt(getSingleObject(list, String.class));
     assertThat(addid, is(7));
        
     }
    
     */
    //注销
    @Test
    public void test_do_logout() {

        List list = getJsonReturn("/company/logout.erd");
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

    }

    //查看周围车辆
    @Test
    public void test_show_car_around() {

        List list = getJsonReturn("/company/show_car_around.erd");
        Head head = getHeader(list);

        assertThat(head.getStatus(), is("ok"));
        assertThat(list.size(), is(2));

        assertThat(((Map) list.get(1)).get("id") + "", is("6.0"));
        assertThat(((Map) list.get(1)).get("username") + "", is("fff"));
        assertThat(((Map) list.get(1)).get("car_number") + "", is("苏A123459"));
        assertThat(((Map) list.get(1)).get("phone_number") + "", is("6"));
        assertThat(((Map) list.get(1)).get("latitude") + "", is("32.04"));
        assertThat(((Map) list.get(1)).get("longtitude") + "", is("118.77"));
        assertThat(((Map) list.get(1)).get("province") + "", is("江苏省"));
        assertThat(((Map) list.get(1)).get("city") + "", is("南京市"));
    }

    //发布订单
    @Test
    public void test_do_send_order() {

        OrderBean param = new OrderBean("444", "2012-1-1", "2012-1-1", "111", "撒范德萨", "北京", "上海", "撒范", 1);
        List list = getJsonReturn("/company/send_order.erd", param);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

//        int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
//        assertThat(addid, is(88));
    }

    //得到正在发布的订单列表
    @Test
    public void test_do_get_bidding_order_list() {
        IdBean companyId = new IdBean("1");
        List<Map<String, Object>> list = getJsonReturn("/company/get_bidding_order_list.erd", companyId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        //如果是多个数据就是getlistObject()方法
        assertThat(list.size() + "", is("8"));

        assertThat(list.get(1).get("expect_fare") + "", is("111.0"));
        assertThat(list.get(1).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(1).get("expect_end_time") + "", is("2012-02-01"));
        assertThat(list.get(1).get("sketch") + "", is("狗"));
        assertThat(list.get(1).get("username") + "", is("aaa"));
        assertThat(list.get(1).get("price") + "", is("111.0"));
        assertThat(list.get(1).get("phone_number") + "", is("11111111111"));
        assertThat(list.get(1).get("car_number") + "", is("苏E12345"));
        assertThat(list.get(1).get("start_address") + "", is("南京"));
        assertThat(list.get(1).get("destination") + "", is("苏州"));

        assertThat(list.get(2).get("expect_fare") + "", is("111.0"));
        assertThat(list.get(2).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(2).get("expect_end_time") + "", is("2012-02-01"));
        assertThat(list.get(2).get("sketch") + "", is("狗"));
        assertThat(list.get(2).get("price") + "", is("113.0"));
        assertThat(list.get(2).get("username") + "", is("ddd"));
        assertThat(list.get(2).get("phone_number") + "", is("4"));
        assertThat(list.get(2).get("car_number") + "", is("苏A123457"));
        assertThat(list.get(2).get("start_address") + "", is("南京"));
        assertThat(list.get(2).get("destination") + "", is("苏州"));

        assertThat(list.get(3).get("expect_fare") + "", is("111.0"));
        assertThat(list.get(3).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(3).get("expect_end_time") + "", is("2012-02-01"));
        assertThat(list.get(3).get("sketch") + "", is("狗"));
        assertThat(list.get(3).get("price") + "", is("114.0"));
        assertThat(list.get(3).get("username") + "", is("eee"));
        assertThat(list.get(3).get("phone_number") + "", is("5"));
        assertThat(list.get(3).get("car_number") + "", is("苏E123458"));
        assertThat(list.get(3).get("start_address") + "", is("南京"));
        assertThat(list.get(3).get("destination") + "", is("苏州"));

        assertThat(list.get(4).get("expect_fare") + "", is("111.0"));
        assertThat(list.get(4).get("start_time") + "", is("2012-01-01"));
        assertThat(list.get(4).get("expect_end_time") + "", is("2012-02-01"));
        assertThat(list.get(4).get("sketch") + "", is("狗"));
        assertThat(list.get(4).get("price") + "", is("115.0"));
        assertThat(list.get(4).get("username") + "", is("fff"));
        assertThat(list.get(4).get("phone_number") + "", is("6"));
        assertThat(list.get(4).get("car_number") + "", is("苏A123459"));
        assertThat(list.get(4).get("start_address") + "", is("南京"));
        assertThat(list.get(4).get("destination") + "", is("苏州"));

    }

    //订单成交
    @Test
    public void test_do_deal_order() {

        SelectBidBean sbb = new SelectBidBean("1", "1", "1", "111");
        List list = getJsonReturn("/company/deal_order.erd", sbb);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

//     int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
//     assertThat(addid, is(73));
    }

    //得到正在运行的订单的列表
    @Test
    public void test_do_get_executing_order() {

        IdBean companyId = new IdBean("2");
        List<Map<String, Object>> list = getJsonReturn("/company/get_executing_order.erd", companyId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        assertThat(list.size() + "", is("3"));
        assertThat(list.get(1).get("id") + "", is("2.0"));
        assertThat(list.get(1).get("sketch") + "", is("材料"));
        assertThat(list.get(1).get("username") + "", is("hhh"));
        assertThat(list.get(1).get("order_id") + "", is("5.0"));
        assertThat(list.get(1).get("car_number") + "", is("苏E8888"));
        assertThat(list.get(1).get("phone_number") + "", is("8"));
        assertThat(list.get(1).get("start_address") + "", is("常州"));
        assertThat(list.get(1).get("destination") + "", is("镇江"));
        assertThat(list.get(1).get("longtitude") + "", is("122.11"));
        assertThat(list.get(1).get("latitude") + "", is("132.11"));

        assertThat(list.get(2).get("id") + "", is("2.0"));
        assertThat(list.get(2).get("sketch") + "", is("生鲜"));
        assertThat(list.get(2).get("username") + "", is("ggg"));
        assertThat(list.get(2).get("order_id") + "", is("6.0"));
        assertThat(list.get(2).get("car_number") + "", is("苏E7777"));
        assertThat(list.get(2).get("phone_number") + "", is("7"));
        assertThat(list.get(2).get("start_address") + "", is("深圳"));
        assertThat(list.get(2).get("destination") + "", is("南昌"));
        assertThat(list.get(2).get("longtitude") + "", is("122.11"));
        assertThat(list.get(2).get("latitude") + "", is("132.11"));

    }

    //得到正在运行的订单的详情
    @Test
    public void test_do_get_executing_order_detail() {

        IdBean orderId = new IdBean("1");
        List list = getJsonReturn("/company/get_executing_order_detail.erd", orderId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));
        assertThat(list.size() + "", is("2"));

        assertThat(((Map) list.get(1)).get("order_number") + "", is("111"));
        assertThat(((Map) list.get(1)).get("start_time") + "", is("2012-01-01"));
        assertThat(((Map) list.get(1)).get("expect_end_time") + "", is("2012-02-01"));
        assertThat(((Map) list.get(1)).get("description") + "", is("来自北方的车"));
        assertThat(((Map) list.get(1)).get("sketch") + "", is("车"));
        assertThat(((Map) list.get(1)).get("start_address") + "", is("北京"));
        assertThat(((Map) list.get(1)).get("destination") + "", is("上海"));
        assertThat(((Map) list.get(1)).get("username") + "", is("aaa"));
        assertThat(((Map) list.get(1)).get("car_number") + "", is("苏E12345"));
        assertThat(((Map) list.get(1)).get("phone_number") + "", is("11111111111"));
        assertThat(((Map) list.get(1)).get("latitude") + "", is("130.11"));
        assertThat(((Map) list.get(1)).get("longtitude") + "", is("120.11"));

        //如果是多个数据就是getlistObject()方法
    }

    //结束订单
    @Test
    public void test_do_finish_order() {

        FinishOrderBean foBean = new FinishOrderBean("1", "1", 2, "非常好", "2012-02-03", "2012-01-03");
        List list = getJsonReturn("/company/finish_order.erd", foBean);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

//        int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
//        assertThat(addid, is(1));
    }

    //取消发布中的订单
    @Test
    public void test_do_cancel_launching_order() {

        CancelLaunchingOrderBean clob = new CancelLaunchingOrderBean(1, 1);
        List list = getJsonReturn("/company/cancel_launching_order.erd", clob);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

    }

    //取消运行中的订单
    @Test
    public void test_do_cancel_executing_order() {

        CancelExecutingOrderBean clob = new CancelExecutingOrderBean("1", "1", "太慢", 2);
        List list = getJsonReturn("/company/cancel_executing_order.erd", clob);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

//        int addid = Integer.parseInt(getSingleObject(list, String.class));//如果是多个数据就是getlistObject()方法
//        assertThat(addid, is(21));
    }

    //获得历史订单列表
    @Test
    public void test_do_get_history_order_list() {

        IdBean companyId = new IdBean("1");
        List list = getJsonReturn("/company/get_history_order_list.erd", companyId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        assertThat(list.size(), is(2));
        assertThat(((Map) list.get(1)).get("order_number") + "", is("555"));
        assertThat(((Map) list.get(1)).get("last_time") + "", is("31.0"));
        assertThat(((Map) list.get(1)).get("start_address") + "", is("香港"));
        assertThat(((Map) list.get(1)).get("destination") + "", is("广州"));
        assertThat(((Map) list.get(1)).get("sketch") + "", is("猫"));
        assertThat(((Map) list.get(1)).get("username") + "", is("aaa"));
        assertThat(((Map) list.get(1)).get("exact_fare") + "", is("111.0"));
    }

    //得到历史订单详情
    @Test
    public void test_do_get_history_order_detail() {

        HistoryOrderDetailBean hodb = new HistoryOrderDetailBean("555");
        List<Map<String, Object>> list = getJsonReturn("/company/get_history_order_detail.erd", hodb);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        assertThat(list.size(), is(2));

        assertThat((list.get(1)).size() + "", is("14"));
        assertThat((list.get(1)).get("company_id") + "", is("1.0"));
        assertThat((list.get(1)).get("order_number") + "", is("555"));
        assertThat((list.get(1)).get("last_time") + "", is("31.0"));
        assertThat((list.get(1)).get("exact_fare") + "", is("111.0"));
        assertThat((list.get(1)).get("expect_fare") + "", is("111.0"));
        assertThat((list.get(1)).get("expect_end_time") + "", is("2012-02-01"));
        assertThat((list.get(1)).get("exact_end_time") + "", is("2012-02-01"));
        assertThat((list.get(1)).get("start_time") + "", is("2012-01-01"));
        assertThat((list.get(1)).get("start_address") + "", is("香港"));
        assertThat((list.get(1)).get("destination") + "", is("广州"));
        assertThat((list.get(1)).get("sketch") + "", is("猫"));
        assertThat((list.get(1)).get("description") + "", is("来自西方的猫"));
        assertThat((list.get(1)).get("username") + "", is("aaa"));

    }

    //获得司机的评价
    @Test
    public void test_do_get_car_remark() {

        IdBean carId = new IdBean("1");
        List list = getJsonReturn("/company/get_car_remark.erd", carId);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        assertThat(((Map) list.get(1)).get("evaluate") + "", is("2"));
        assertThat(((Map) list.get(1)).get("remark") + "", is("good"));
        assertThat(((Map) list.get(1)).get("username") + "", is("aaa"));
        assertThat(((Map) list.get(1)).get("car_number") + "", is("苏E12345"));
        assertThat(((Map) list.get(1)).get("company_name") + "", is("e路网"));

        System.out.println(list.toString());
    }

}
