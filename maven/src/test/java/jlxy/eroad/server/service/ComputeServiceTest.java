package jlxy.eroad.server.service;

import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.result.ComputeResult;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.baseframework.test.Base;

/**
 *
 * 
 */
public class ComputeServiceTest extends Base {

    @Autowired
    private ComputeService cs;

    @Test
    public void test_compute() {
        ComputeParam param = new ComputeParam(3, 4);
        Sret sr = cs.compute(param);
        assertThat(sr.isOk(), is(true));
        ComputeResult result = (ComputeResult) sr.getData();
        assertThat(result.getResult(), is(7));
    }

    @Test
    public void test_throwException() {
        Sret sr = cs.throwException();
        assertThat(sr.isFail(), is(true));
        assertThat(sr.getMessage(), is("错误的状态"));
    }
}
