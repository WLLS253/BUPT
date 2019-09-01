package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Serivce.UploadSerivce;
import com.example.demo.Util.Util;
import com.example.demo.entity.TestJax;
import com.example.demo.repository.TestJaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class fileController {


    @Autowired
    private UploadSerivce uploadSerivce;

    @Autowired
    private TestJaxRepository testJaxRepository;

    @PostMapping(value = "/test/file")
    public Result upfile(@RequestParam("file")MultipartFile file){
        try {
            String path= uploadSerivce.upImageFire(file);
            return Util.success(path);
        }catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.USER_TEL_ERROR);
        }

    }


    @PostMapping(value = "/test/email")
    public Result testemail(@RequestParam("email") String email){
        TestJax testJax=new TestJax();
        testJax.setEmail(email);
        return Util.success(testJaxRepository.save(testJax));
    }


}
