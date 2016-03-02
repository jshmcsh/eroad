package jlxy.eroad.server.web.controller;

import com.google.gson.Gson;
import java.util.List;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.baseframework.web.ControllerBase;
import org.yecq.baseframework.web.Logged;

/**
 *
 * @author cp
 */
@Controller
@RequestMapping("/company/")
public class CompanyController extends ControllerBase {

    @Autowired
    private CompanyService cs;

    @RequestMapping("regist.erd")
    @ResponseBody
    public List do_regist(@RequestParam("json") String json) {
        LoginBean param = new Gson().fromJson(json, LoginBean.class);
        Sret sr = cs.regist(param);
        return getRetList(sr);
    }
   
    @RequestMapping("login.erd")
    @ResponseBody
    public List do_login(@RequestParam("json") String json) {
        LoginBean param = new Gson().fromJson(json, LoginBean.class);
        Sret sr = cs.login(param);
        return getRetList(sr);
    }
    
    @RequestMapping("throw_exception.erd")
    @ResponseBody
    public List do_throwException() {
        Sret sr = cs.throwException();
        return getRetList(sr);
    }
}
