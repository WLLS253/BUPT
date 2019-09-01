//package com.example.demo.plugins;
//
//
//import com.example.demo.Serivce.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@Component
//public class MyInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private TokenService tokenService;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url=request.getRequestURI();
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getMethod());
//        System.out.println(url);
//        if(url.equals("/t/login")||url.equals("/t/user/add")){
//            return  true;
//        }
//        String token=request.getHeader("token");
//        if(token!=null){
//            System.out.println(token);
//            String uuid =tokenService.validateToken(token,24 * 3600L);
//            if(uuid==null){
//                System.out.println("token有误");
//                return  false;
//            }
//        }
//        System.out.println("pre");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("lll");
//    }
//}
