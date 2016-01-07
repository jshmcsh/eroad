package jlxy.eroad.server.web.controller;

import java.util.List;
import jlxy.eroad.server.bean.param.ComputeParam;
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
public class ComputeControllerTest extends IntegrateBase {

    @Test
    public void test_do_compute() {
        ComputeParam param = new ComputeParam(4, 7);
        List list = getJsonReturn("/test/compute.erd", param);
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("ok"));

        ComputeResult result = getSingleObject(list, ComputeResult.class);
        assertThat(result.getResult(), is(11));
    }

    @Test
    public void test_throwException() {
        List list = getJsonReturn("/test/throw_exception.erd");
        Head head = getHeader(list);
        assertThat(head.getStatus(), is("fail"));
        assertThat(head.getMessage(), is("错误的状态")
        );

    }
}
