package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Serivce.UploadSerivce;
import com.example.demo.Util.Util;
import com.example.demo.entity.Thing;
import com.example.demo.entity.Timage;
import com.example.demo.entity.User;
import com.example.demo.entity.UserSelling;
import com.example.demo.repository.ThingRepository;
import com.example.demo.repository.TimageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSellingRepository;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ThingController  {

    @Autowired
    private ThingRepository thingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimageRepository timageRepository;

    @Autowired
    private UserSellingRepository userSellingRepository;

    @Autowired
    private UploadSerivce uploadSerivce;

    @PostMapping(value = "/thing/add")
    public Result addThing(Thing thing, @RequestParam("file")MultipartFile file,@RequestParam("user_id")Long id){
        try {
            User user=userRepository.findById(id).get();
            UserSelling userSelling=userSellingRepository.findAllByUser(user).get(0);
            String path=uploadSerivce.upImageFire(file);
            Timage timage=new Timage();
            timage.setImage(path);
            Timage timage1=timageRepository.save(timage);
            List<Timage> timageList=new ArrayList<Timage>();
            timageList.add(timage1);
            thing.setTimageList(timageList);
            thing.setUser(user);
            userSelling.setUser(user);
            List<Thing> thingList=userSelling.getThingList();
            thingList.add(thing);
            List<Thing> thingList1=user.getThingList();
            thingList1.add(thing);
            Thing thing1=thingRepository.save(thing);
            timage1.setThing(thing);
            timageRepository.save(timage1);
            userSellingRepository.save(userSelling);
            user.setThingList(thingList1);
            userRepository.save(user);
            return Util.success(thing1);

        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNFIND_ERROR);
        }
    }


    @PutMapping(value = "/things/update/{id}")
    public Result update(@PathVariable("id")Long id,Thing thing){
        try {
            Thing thing1=thingRepository.findById(id).get();
            thing1.settPrice(thing.gettPrice());
            thing1.setHistory(thing.isHistory());
            thing1.setState(thing.isState());
            thing1.settContent(thing.gettContent());
            return  Util.success(thingRepository.save(thing1));

        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UPDATE_ERROR);

        }
    }

    @GetMapping(value = "/thing/get/{id}")
    public  Result getThing(@PathVariable("id")Long id){
        try {
            Thing thing=thingRepository.findById(id).get();
            return Util.success(thing);
        }catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNFIND_ERROR);
        }
    }







}
