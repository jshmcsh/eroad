package jlxy.eroad.server.web.controller;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.Position;
import jlxy.eroad.server.bean.param.company.OrderBean;
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

    @RequestMapping("logout.erd")
    @ResponseBody
    public List do_logout(HttpServletRequest req) {
        Sret sr = new Sret();
        req.getSession().invalidate();
        return getRetList(sr);
    }

    @RequestMapping("show_car_around.erd")
    @ResponseBody
    //@Logged
    public List do_show_car_around(@RequestParam("json") String json) {  
        Position param = new Gson().fromJson(json, Position.class);
        Sret sr = cs.ShowCarAround(param);
        return getRetList(sr);
    }
    
    @RequestMapping("send_order.erd")
    @ResponseBody
    //@Logged
    public List do_send_order(@RequestParam("json") String json) {  
        OrderBean param = new Gson().fromJson(json, OrderBean.class);
        Sret sr = cs.send_order(param);
        return getRetList(sr);
    }
    
    @RequestMapping("throw_exception.erd")
    @ResponseBody
    public List do_throwException() {
        Sret sr = cs.throwException();
        return getRetList(sr);
    }
}
