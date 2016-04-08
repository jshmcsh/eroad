/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.eroad.server.web.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author CP
 */
@WebFilter(urlPatterns = "*.erd")
public class CrossFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        ((HttpServletResponse) response).addHeader(
                "Access-Control-Allow-Origin", "*"
        );
        ((HttpServletResponse) response).addHeader(
                "Access-Control-Allow-Credentials", "true" // qwest.get(url, null, { withCredentials: true }); //允许跨域设置cookie
        );
        chain.doFilter(request, response);
        
    }
    
    @Override
    public void destroy() {
    }
    
}
