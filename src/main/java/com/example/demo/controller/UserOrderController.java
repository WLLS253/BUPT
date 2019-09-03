package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Util.Util;
import com.example.demo.entity.Thing;
import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.repository.OrderFormRepository;
import com.example.demo.repository.ThingRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserOrderController {
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderFormRepository orderFormRepository;

    @Autowired
    private ThingRepository thingRepository;


    @RequestMapping(value = "/userOrder/add")
    public Result addUserOrder(@RequestParam("t_id")Long t_id,@RequestParam("user_id")Long user_id,@RequestParam("date") Date date){
        try {
            Thing thing=thingRepository.findById(t_id).get();
            User user=userRepository.findById(user_id).get();
            if(thing.isHistory()){
                return Util.failure(ExceptionEnums.THING_UNFIND);
            }
            List<UserOrder>userOrderList=userOrderRepository.findByUser(user);
            if(userOrderList.size()==0){
                UserOrder userOrder=new UserOrder();
                userOrder.setUser(user);
                List<Thing> thingList=new ArrayList<Thing>();
                thingList.add(thing);
                userOrder.setThingList(thingList);
                userOrder.setDate(date);
                return  Util.success(userOrderRepository.save(userOrder));
            }else {
                UserOrder userOrder=userOrderList.get(0);
                List<Thing>thingList=userOrder.getThingList();
                thingList.add(thing);
                userOrder.setThingList(thingList);
                userOrder.setDate(date);
                return  Util.success(userOrderRepository.save(userOrder));
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }

    }

    @GetMapping(value = "/userorder/get/{id}")
    public  Result getUserOrder(@PathVariable("user_id")Long id){
        try {
            User user=userRepository.findById(id).get();
            List<UserOrder>userOrderList=userOrderRepository.findByUser(user);
            if(userOrderList.size()==0){
                return  Util.failure(ExceptionEnums.USER_EXIST_ERROR);
            }else {
                UserOrder userOrder=userOrderList.get(0);
                return  Util.success(userOrder);
            }

        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }

    }


}
