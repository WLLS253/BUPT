package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Serivce.CookieService;
import com.example.demo.Serivce.TokenService;
import com.example.demo.Util.Util;
import com.example.demo.entity.Thing;
import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.entity.UserSelling;
import com.example.demo.repository.ThingRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ReferenceType;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserSellingRepository userSellingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThingRepository thingRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UserOrderRepository userOrderRepository;


    @PostMapping(value = "/login",produces = "application/json; charset=utf-8")
    public Result Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){

        try {
            System.out.println(username);
            List<User> userList=userRepository.findByUsername(username);
            boolean valid = false;
            if (userList.size()!=0) {
                if (userList.get(0).checkPassword(password)) {
                    String token = tokenService.generateToken(String.valueOf(userList.get(0).getId()));
                    response.setHeader("isLogin", "true");
                    response.setHeader("token", token);
                    cookieService.writeCookie(response,username,username);
                    return Util.success(userList.get(0));
                }
            }
            response.setHeader("isLogin", "false");
            return  Util.failure(ExceptionEnums.PASSWORD_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response.setHeader("isLogin", "false");
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }

    @PostMapping(value = "/user/add")
    public  Result addUser(User user,HttpServletResponse response){

        try {
            System.out.println(user);
            user.setUsername(user.getUsername());
            user.setPasswordT(user.getPassword());
            user.setTel(user.getTel());
            user.setEmail(user.getEmail());
            user.setGrade(user.isGrade());
            user.setWechat(user.getWechat());
            user.setQq(user.getQq());
            System.out.println(user);
            User  user1=userRepository.save(user);
            UserSelling userSelling=new UserSelling();
            userSelling.setUser(user);
            userSellingRepository.save(userSelling);
            UserOrder userOrder=new UserOrder();
            userOrder.setUser(user);
            userOrderRepository.save(userOrder);
            String token = tokenService.generateToken(String.valueOf(user1.getId()));
            response.setHeader("isLogin", "true");
            response.setHeader("token", token);
            cookieService.writeCookie(response,"sessionid",user.getUsername());
            return Util.success(user);
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }


    }

    @DeleteMapping(value = "/user/del/{id}")
    public  Result delUser(@PathVariable("id")Long id){

        try {
            userRepository.deleteById(id);
            return Util.success(ExceptionEnums.DEL_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }

    }

    @PutMapping(value = "/user/put/{id}")
    public Result updateUser(@PathVariable("id")Long id,User user){

        try {
            User user1=userRepository.findById(id).get();
            user.setId(user1.getId());
            return  Util.success(userRepository.save(user));
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UPDATE_ERROR);
        }

    }


}
