/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.eroad.server.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import jlxy.eroad.server.bean.param.company.LocationBean;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author CP
 */
public class Util {

    public String getIpAddr(HttpServletRequest request) {

        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public LocationBean getCityNameByIp(String ip) throws ClientProtocolException,
            IOException {
        System.out.println("**********************3********************");
        String url = "http://api.map.baidu.com/location/ip?ak=r3gBlVMFlVD33uNirMBDmmvl&ip=" + ip + "&coor=bd09ll";
        String city = "";
        String province = "";
        String latitude = "";
        String longtitude = "";
       
        LocationBean lb = new LocationBean();
        HttpGet get = new HttpGet(url);

        // 使用HttpClient发送get请求，获得返回结果HttpResponse
        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(get);

        // 读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) {
            // 获取城市
            String entityString = EntityUtils.toString(response.getEntity());

            JSONObject obj = JSONObject.fromObject(entityString);
            JSONObject contentObj = JSONObject.fromObject(obj.getString("content"));
            JSONObject pointObj = JSONObject.fromObject(contentObj.getString("point"));
            JSONObject addressDetailObj = JSONObject.fromObject(contentObj.getString("address_detail"));
            province = addressDetailObj.getString("province");
            city = addressDetailObj.getString("city");

            longtitude = pointObj.getString("y");
            latitude = pointObj.getString("x");
            lb.setProvince(province);
            lb.setCity(city);
            lb.setLongtitude(longtitude);
            lb.setLatitude(latitude);
        }
        return lb;
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1 第一点纬度
     * @param long2 第二点经度
     * @param lat2 第二点纬度
     * @return 返回距离 单位：米
     */
    public double Distance(double long1, double lat1, double long2,
            double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }
}
