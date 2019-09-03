package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Util.Util;
import com.example.demo.entity.OrderForm;
import com.example.demo.entity.Thing;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderFormRepository;
import com.example.demo.repository.ThingRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ReferenceType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private OrderFormRepository orderFormRepository;

    @Autowired
    private ThingRepository thingRepository;

    @GetMapping(value = "/order/add/{date}")
    public Result addOrder(@PathVariable("date") String date){
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date date1=simpleDateFormat.parse(date);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DATE,1);
            date1=calendar.getTime();
            calendar.add(Calendar.DATE,-1);
            Date date2=calendar.getTime();
            List<OrderForm> orderFormList=orderFormRepository.findAllByDateBeforeAndDateAfter(date1,date2);
            if(orderFormList.size()==0){
                return Util.failure(ExceptionEnums.UNFIND_ERROR);

            }else {
                return  Util.success(orderFormList);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }

    @DeleteMapping(value = "/order/del/{id}")
    public  Result delOrder(@PathVariable("id") Long id){
        try {
            orderFormRepository.deleteById(id);
            return Util.success(ExceptionEnums.DEL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }

    }

    @PostMapping(value = "/order/add")
    public Result addOrder(@RequestParam("date") Date date,@RequestParam("user_id") Long user,@RequestParam("thing_id")Long thing){

        try {
            OrderForm orderForm=new OrderForm();
            orderForm.setDate(date);
            User user1=userRepository.findById(user).get();
            Thing thing1=thingRepository.findById(thing).get();
            orderForm.setUser(user1);
            orderForm.setThing(thing1);
            return Util.success(orderFormRepository.save(orderForm));
        }catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }

    }







}
