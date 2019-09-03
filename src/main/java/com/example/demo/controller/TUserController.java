//package com.example.demo.controller;
//
//
//import com.example.demo.Enums.ExceptionEnums;
//import com.example.demo.Result.Result;
//import com.example.demo.Serivce.CookieService;
//import com.example.demo.Serivce.TokenService;
//import com.example.demo.Serivce.UploadSerivce;
//import com.example.demo.Util.Util;
//import com.example.demo.entity.TUser;
//import com.example.demo.repository.ScheduleRepository;
//import com.example.demo.repository.TUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@RestController
//public class TUserController  {
//
//    @Autowired
//    private TUserRepository tUserRepository;
//
//    @Autowired
//    private  UploadSerivce uploadSerivce;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private  CookieService cookieService;
//
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//
//
//
//
//    @PostMapping(value = "/login",produces = "application/json; charset=utf-8")
//    public Result Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
//
//        try {
//            System.out.println(username);
//            List<TUser> tUsers=tUserRepository.findByUsername(username);
//            boolean valid = false;
//            if (tUsers.size()!=0) {
//                if (tUsers.get(0).checkPassword(password)) {
//                    String token = tokenService.generateToken(String.valueOf(tUsers.get(0).getId()));
//                    response.setHeader("isLogin", "true");
//                    response.setHeader("token", token);
//                    cookieService.writeCookie(response,username,username);
//                    return Util.success(tUsers.get(0));
//                }
//            }
//            response.setHeader("isLogin", "false");
//            return  Util.failure(ExceptionEnums.PASSWORD_ERROR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setHeader("isLogin", "false");
//            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//
//
//    }
//
//    @PostMapping(value = "/user/add")
//    public Result addUser(TUser user,HttpServletResponse response) {
//        try {
//            user.setUsername(user.getUsername());
//            user.setPasswordT(user.getPassword());
//            user.setTel(user.getTel());
//            user.setEmail(user.getEmail());
//            user.setGrade(user.getGrade());
//            user.setWechat(user.getWechat());
//            user.setQq(user.getQq());
//            System.out.println(user);
//            TUser tUser=tUserRepository.save(user);
//            String token = tokenService.generateToken(String.valueOf(tUser.getId()));
//            response.setHeader("isLogin", "true");
//            response.setHeader("token", token);
//            cookieService.writeCookie(response,"sessionid",user.getUsername());
//            return Util.success(user);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//
//
//    }
//
//
//    @PutMapping(value = "/user/update/{id}")
//    public  Result updateUser(@PathVariable("id")Long id,TUser tUser){
//
//        try {
//            TUser tUser1=tUserRepository.findById(id).get();
//            tUser1.setUsername(tUser.getUsername());
//            tUser1.setPassword(tUser.getPassword());
//            tUser1.setEmail(tUser.getEmail());
//            tUser1.setQq(tUser.getQq());
//            tUser1.setWechat(tUser.getWechat());
//            tUser1.setGrade(tUser.getGrade());
//            tUser1.setTel(tUser.getTel());
//            return  Util.success(tUserRepository.save(tUser1));
//        }catch (Exception e){
//            e.printStackTrace();
//            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//    @PutMapping(value = "/user/password/{name}")
//    public Result passUser(@PathVariable("name") String username,String password){
//        try {
//            List<TUser> tUser1=tUserRepository.findByUsername(username);
//            if(tUser1.size()>0){
//                TUser tUser=tUser1.get(0);
//                tUser.setPassword(password);
//                return  Util.success(tUserRepository.save(tUser));
//            }else {
//                return Util.failure(ExceptionEnums.UNFIND_ERROR);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//
//
//
//    @GetMapping(value = "/pc/list/{id}")
//    public String PCUserList() {
//        return "hhhh";
//    }
//
//
//}
