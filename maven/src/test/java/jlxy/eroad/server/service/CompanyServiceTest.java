package jlxy.eroad.server.service;

import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.baseframework.test.Base;

/**
 *
 * @author yecq
 */
public class CompanyServiceTest extends Base {

    @Autowired
    private CompanyService cs;

    @Test
    public void test_login() {
       LoginBean param=new LoginBean("abcd","123456");
       Sret sr = cs.login(param);
       assertThat(sr.isOk(), is(true));
       
    }

    @Test
    public void test_throwException() {
        Sret sr = cs.throwException();
        assertThat(sr.isFail(), is(true));
        assertThat(sr.getMessage(), is("错误的状态"));
    }
}
