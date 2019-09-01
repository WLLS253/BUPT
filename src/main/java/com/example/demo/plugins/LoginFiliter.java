//package com.example.demo.plugins;
//
//import com.example.demo.Serivce.CookieService;
//import com.example.demo.Serivce.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureOrder;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.URLEncoder;
//
//@Order(1)
//@WebFilter(filterName="firstFilter", urlPatterns="/*")
//public class LoginFiliter implements Filter {
//
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        System.out.println("firstliter");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response=(HttpServletResponse)servletResponse;
//        request.setAttribute("user", "0");
//        String token = request.getHeader("token");
//        System.out.println(token);
//        if (token != null) {
//            String uuid = tokenService.validateToken(token, 24 * 3600L);
//            System.out.println(uuid);
//            if (uuid != null) request.setAttribute("user", uuid);
//        }
//        System.out.println("dofilterhhhhh");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("destroy");
//    }
//
//
//
//}
//
//
