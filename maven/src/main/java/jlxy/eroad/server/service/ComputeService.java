package jlxy.eroad.server.service;

import javax.transaction.Transactional;
import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.result.ComputeResult;
import org.springframework.stereotype.Service;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
@Service
@Transactional
public class ComputeService {

    public Sret compute(ComputeParam param) {
        int a = param.getA();
        int b = param.getB();
        int c = a + b;
        ComputeResult ret = new ComputeResult(c);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }

    public Sret throwException() {
        throw new IllegalStateException("错误的状态");
    }
}
