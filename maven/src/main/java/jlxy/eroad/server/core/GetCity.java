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
public class GetCity {
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
    
    public String getCityNameByIp(String ip) throws ClientProtocolException,
			IOException {
		String url = "http://api.map.baidu.com/location/ip?ak=r3gBlVMFlVD33uNirMBDmmvl&ip="+ip+"&coor=bd09ll";
		String city = "";
		String province = "";
		HttpGet get = new HttpGet(url);

		// 使用HttpClient发送get请求，获得返回结果HttpResponse
		HttpClient client = new DefaultHttpClient();

		HttpResponse response = client.execute(get);

		// 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			// 获取城市
			String entityString = EntityUtils.toString(response.getEntity());
			//System.out.println(entityString);

			JSONObject obj = JSONObject.fromObject(entityString);

			province = new String(obj.getString("province"));
			city = new String(obj.getString("city"));

			//System.out.println(province + '\t' + city);

		}
		return city;
	}

}
