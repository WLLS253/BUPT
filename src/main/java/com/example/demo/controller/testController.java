package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping(value = "/index")
    public String test(){
        return "index";
    }

    @RequestMapping(value = "/create_an_account")
    public  String test2(){return "create_an_account";}


}
