package jlxy.eroad.server.web.controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jlxy.eroad.server.bean.param.IdBean;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.param.Position;
import jlxy.eroad.server.bean.param.company.CancelExecutingOrderBean;
import jlxy.eroad.server.bean.param.company.CancelLaunchingOrderBean;
import jlxy.eroad.server.bean.param.company.CompanyBean;
import jlxy.eroad.server.bean.param.company.FinishOrderBean;
import jlxy.eroad.server.bean.param.company.HistoryOrderDetailBean;
import jlxy.eroad.server.bean.param.company.OrderBean;
import jlxy.eroad.server.bean.param.company.RegistBean;
import jlxy.eroad.server.bean.param.company.SelectBidBean;
import jlxy.eroad.server.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.baseframework.web.ControllerBase;
import org.yecq.baseframework.web.Logged;

/**
 *
 * @author CP
 */
@Controller
@RequestMapping("/company/")
public class CompanyController extends ControllerBase {

    private HttpServletRequest request;
    @Autowired
    private CompanyService cs;

    @RequestMapping("regist.erd")
    @ResponseBody
    public List do_regist(@RequestParam("json") String json, @RequestParam("file") MultipartFile file) {
        RegistBean param = new Gson().fromJson(json, RegistBean.class);
        Sret sr = cs.regist(param, file);
        return getRetList(sr);
    }

    @RequestMapping("login.erd")
    @ResponseBody
    public List do_login(@RequestParam("json") String json, HttpServletResponse resp, HttpServletRequest req) {
        LoginBean param = new Gson().fromJson(json, LoginBean.class);
        Sret sr = cs.login(param, req, resp);
        return getRetList(sr);

    }

    @RequestMapping("test_cookie.erd")
    @ResponseBody
    public List do_test_cookie(HttpServletResponse resp, HttpServletRequest req) {
        Sret sr = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if ("JSESSIONID".equals(name) && req.getSession().getId().equals(value)) {
                    sr = new Sret();
                    sr.setOk("已登录");
                    sr.setData((CompanyBean) req.getSession().getAttribute("companyInfo"));
                }
            }
        }
        if (sr == null) {
            sr = new Sret();
            sr.setFail("未登录");
        }
        return getRetList(sr);

    }

    @RequestMapping("logout.erd")
    @ResponseBody
    public List do_logout(HttpServletResponse resp, HttpServletRequest req) {
        Sret sr = new Sret();
        req.getSession().invalidate();
        return getRetList(sr);
    }

    @RequestMapping("show_car_around.erd")
    @ResponseBody
    //@Logged
    public List do_show_car_around(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        Sret sr;
        sr = cs.show_car_around(req);
        return getRetList(sr);
    }

    @RequestMapping("send_order.erd")
    @ResponseBody
    //@Logged
    public List do_send_order(@RequestParam("json") String json, HttpServletResponse resp) {
        OrderBean param = new Gson().fromJson(json, OrderBean.class);
        Sret sr = cs.send_order(param);
        return getRetList(sr);
    }

    @RequestMapping("get_bidding_order_list.erd")
    @ResponseBody
    //  @Logged
    public List do_get_bidding_order_list(@RequestParam("json") String json, HttpServletResponse resp) {
        IdBean company_id = new Gson().fromJson(json, IdBean.class);
        Sret sr = cs.get_bidding_order_list(company_id);
        return getRetList(sr);
    }

    @RequestMapping("deal_order.erd")
    @ResponseBody
    //@Logged
    public List do_deal_order(@RequestParam("json") String json, HttpServletResponse resp) {
        SelectBidBean orderId = new Gson().fromJson(json, SelectBidBean.class);
        Sret sr = cs.deal_order(orderId);
        return getRetList(sr);
    }

    @RequestMapping("get_executing_order.erd")
    @ResponseBody
    //@Logged
    public List do_get_executing_order(@RequestParam("json") String json, HttpServletResponse resp) {
        IdBean companyId = new Gson().fromJson(json, IdBean.class);
        Sret sr = cs.get_executing_order(companyId);
        return getRetList(sr);
    }

    @RequestMapping("get_executing_order_detail.erd")
    @ResponseBody
    //@Logged
    public List do_get_executing_order_detail(@RequestParam("json") String json, HttpServletResponse resp) {
        IdBean orderId = new Gson().fromJson(json, IdBean.class);
        Sret sr = cs.get_executing_order_detail(orderId);
        return getRetList(sr);
    }

    //完成订单
    @RequestMapping("finish_order.erd")
    @ResponseBody
    //@Logged
    public List do_finish_order(@RequestParam("json") String json, HttpServletResponse resp) {
        FinishOrderBean fobean = new Gson().fromJson(json, FinishOrderBean.class);
        Sret sr = cs.finish_order(fobean);
        return getRetList(sr);
    }

    //取消发布中的订单
    @RequestMapping("cancel_launching_order.erd")
    @ResponseBody
    //@Logged
    public List do_cancel_launching_order(@RequestParam("json") String json, HttpServletResponse resp) {
        CancelLaunchingOrderBean clob = new Gson().fromJson(json, CancelLaunchingOrderBean.class);
        Sret sr = cs.cancel_launching_order(clob);
        return getRetList(sr);
    }

    //取消正在运行的订单
    @RequestMapping("cancel_executing_order.erd")
    @ResponseBody
    //@Logged
    public List do_cancel_executing_order(@RequestParam("json") String json, HttpServletResponse resp) {
        CancelExecutingOrderBean ceob = new Gson().fromJson(json, CancelExecutingOrderBean.class);
        Sret sr = cs.cancel_executing_order(ceob);
        return getRetList(sr);
    }

    //得到某车评价
    @RequestMapping("get_car_remark.erd")
    @ResponseBody
    //@Logged
    public List do_get_car_remark(@RequestParam("json") String json, HttpServletResponse resp) {
        IdBean carId = new Gson().fromJson(json, IdBean.class);
        Sret sr = cs.get_car_remark(carId);
        System.out.println(sr.toString());
        return getRetList(sr);

    }

    //得到历史订单列表
    @RequestMapping("get_history_order_list.erd")
    @ResponseBody
    //@Logged
    public List do_get_history_order_list(@RequestParam("json") String json, HttpServletResponse resp) {
        IdBean companyId = new Gson().fromJson(json, IdBean.class);
        Sret sr = cs.get_history_order_list(companyId);
        return getRetList(sr);
    }

    //得到历史订单详情
    @RequestMapping("get_history_order_detail.erd")
    @ResponseBody
    //@Logged
    public List do_get_history_order_detail(@RequestParam("json") String json, HttpServletResponse resp) {
        HistoryOrderDetailBean hodb = new Gson().fromJson(json, HistoryOrderDetailBean.class);
        Sret sr = cs.get_history_order_detail(hodb);
        return getRetList(sr);
    }

    @RequestMapping("throw_exception.erd")
    @ResponseBody
    public List do_throwException() {
        Sret sr = cs.throwException();
        return getRetList(sr);
    }
}
