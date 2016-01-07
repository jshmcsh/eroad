package jlxy.eroad.server.web.controller;

import com.google.gson.Gson;
import java.util.List;
import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.baseframework.web.ControllerBase;

/**
 *
 * @author yecq
 */
@Controller
@RequestMapping("/test/")
public class ComputeController extends ControllerBase {

    @Autowired
    private ComputeService cs;

    @RequestMapping("compute.erd")
    @ResponseBody
    public List do_compute(@RequestParam("json") String json) {
        ComputeParam param = new Gson().fromJson(json, ComputeParam.class);
        Sret sr = cs.compute(param);
        return getRetList(sr);
    }

    @RequestMapping("throw_exception.erd")
    @ResponseBody
    public List do_throwException() {
        Sret sr = cs.throwException();
        return getRetList(sr);
    }
}
