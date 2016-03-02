package jlxy.eroad.server.web.controller;

import java.util.List;
import jlxy.eroad.server.bean.param.LoginBean;
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
        
    }*/ 
     @Test
    public void test_show_car_around() {
  
        List list = getJsonReturn("/company/show_car_around.erd");
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok")); 
        
    }
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
}
