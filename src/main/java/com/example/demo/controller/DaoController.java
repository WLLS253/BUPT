//package com.example.demo.controller;
//
//
//import com.example.demo.Enums.ExceptionEnums;
//import com.example.demo.Result.Result;
//
//import com.example.demo.Serivce.UploadSerivce;
//import com.example.demo.Util.Util;
//import com.example.demo.entity.inkscreen;
//import com.example.demo.entity.Schedule;
//import com.example.demo.entity.TUser;
//import com.example.demo.repository.inkscreenRepository;
//import com.example.demo.repository.ScheduleRepository;
//import com.example.demo.repository.TUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@RestController
//public class DaoController {
//
//    @Autowired
//    private inkscreenRepository inkscreenRepository;
//
//    @Autowired
//    private TUserRepository tUserRepository;
//
//    @Autowired
//    private UploadSerivce uploadSerivce;
//
//
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//
//    @PostMapping(value = "/inkscreen/add")
//    public Result  addEvent(inkscreen inkscreen, @RequestParam("tuser_id")Long tuser_id, @RequestParam("file")MultipartFile file){
//        try {
//            TUser tUser=tUserRepository.findById(tuser_id).get();
//            Date date= inkscreen.getStartTime();
//             List<Schedule>scheduleList=scheduleRepository.findAllByTUserAndAndDate(tUser,date);
//            if(scheduleList.size()==0) {
//                Schedule schedule = new Schedule();
//                schedule.setDate(date);
//                schedule.settUser(tUser);
//                scheduleRepository.save(schedule);
//            }
//            Schedule schedule1=scheduleRepository.findAllByTUserAndAndDate(tUser,date).get(0);
//            List<inkscreen> inkscreenList1 =schedule1.getInkscreenList();
//            System.out.println(schedule1);
//            String  contengpath=uploadSerivce.upImageFire(file);
//            inkscreen.setEname(inkscreen.getEname());
//            inkscreen.setContent(contengpath);
//            inkscreen.setEndTime(inkscreen.getEndTime());
//            inkscreen.setStartTime(inkscreen.getStartTime());
//            inkscreen.setLocation(inkscreen.getLocation());
//            inkscreen.setFinish(inkscreen.isFinish());
//            List<inkscreen> inkscreenList =tUser.getInkscreens();
//            System.out.println(inkscreenList);
//            System.out.println(inkscreenList1);
//            inkscreenList.add(inkscreen);
//            inkscreen.settUser(tUser);
//            tUser.setInkscreens(inkscreenList);
//            System.out.println(inkscreenList);

//            return Util.success(inkscreenRepository.save(inkscreen));
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//    @GetMapping(value = "/inscreen/get/id/{id}")
//    public  Result getInk(@PathVariable("id") Long id){
//        try {
//            inkscreen inkscreen=inkscreenRepository.findById(id).get();
//            return  Util.success(inkscreen);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//
//    }
//
//    @GetMapping(value = "/inkscreen/get/location/{location}")
//    public Result getInkLoction(@PathVariable("lcoation") String location){
//        try {
//            List<inkscreen> inkscreenList=inkscreenRepository.findAllByLocation(location);
//            if(inkscreenList.size()>0){
//                return Util.success(inkscreenList);
//            }else {
//                return Util.failure(ExceptionEnums.UNFIND_ERROR);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//
//    }
//
//    @PutMapping(value = "/inkscreen/updateink/{id}")
//    public Result updateInk(@PathVariable("id")Long id,inkscreen inkscreen,@RequestParam("file")MultipartFile file){
//        try {
//            inkscreen inkscreen1=inkscreenRepository.findById(id).get();
//            inkscreen1.setLocation(inkscreen.getLocation());
//            uploadSerivce.deleteimage(inkscreen1.getContent());
//            String path=uploadSerivce.upImageFire(file);
//            inkscreen1.setContent(path);
//            inkscreen1.setEname(inkscreen.getEname());
//            inkscreen1.setEndTime(inkscreen.getEndTime());
//            inkscreen1.setStartTime(inkscreen.getStartTime());
//            inkscreen1.setFinish(inkscreen.isFinish());
//            inkscreen1.setFinishTime(inkscreen.getFinishTime());
//            inkscreen1.settUser(inkscreen.gettUser());
//            return  Util.success(inkscreenRepository.save(inkscreen1));
//        }catch (Exception e){
//            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//    @DeleteMapping(value = "/inkscreen/del/{id}")
//    public Result delInke(@PathVariable("id")Long id){
//        try {
//            inkscreenRepository.deleteById(id);
//            return Util.success(ExceptionEnums.DEL_SUCCESS);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//
//    }
//
//
//
//    //请求出某人所控制过的的屏幕
//    @GetMapping(value = "/inkscreen/sort/{name}")
//    public  Result inkSort(@PathVariable("name") String name){
//    try {
//            TUser tUser=tUserRepository.findByUsername(name).get(0);
//            List<inkscreen> inkscreenList =tUser.getInkscreens();
//            return Util.success(inkscreenList);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//    //请求出某人正在的的屏幕
//    @GetMapping(value = "/inkscreen/using/{name}/")
//    public  Result inkingSort(@PathVariable("name") String name){
//        try {
//            TUser tUser=tUserRepository.findByUsername(name).get(0);
//            List<inkscreen> inkscreenList =inkscreenRepository.findAllByTUserAndFinish(tUser,false);
//            return Util.success(inkscreenList);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//
//    //精确到天yyyy-MM-dd，找到某人某一天的安排
////    @GetMapping(value = "/sche/find/{name}/{date}")
////    public Result findSche(@PathVariable("name") String name,@PathVariable("date") String date){
////        try {
////            TUser tUser=tUserRepository.findByUsername(name).get(0);
////            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
////            Date date1=simpleDateFormat.parse(date);
////            List<Schedule> scheduleList=scheduleRepository.findAllByTUserAndAndDate(tUser,date1);
////            if(scheduleList.size()==0){
////                return Util.failure(ExceptionEnums.UNFIND_ERROR);
////            }else {
////                Schedule schedule=scheduleList.get(0);
////                List<inkscreen> inkscreenList =schedule.getInkscreenList();
////                ArrayList<V>vArrayList=daoService.daoSort(inkscreenList);
////                return Util.success(vArrayList);
////            }
////        }catch (Exception e){
////            e.printStackTrace();
////            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
////        }
////    }
//    //TestJax 测试返回某人所控制的一天中的显示的屏幕
//    @GetMapping(value = "/test/{name}/{date}")
//    public Result testSch(@PathVariable("name") String name,@PathVariable("date") String date){
//        try {
//            TUser tUser=tUserRepository.findByUsername(name).get(0);
//            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            Date date1=simpleDateFormat.parse(date);
//            Calendar calendar=Calendar.getInstance();
//            calendar.setTime(date1);
//            calendar.add(Calendar.DATE,1);
//            date1=calendar.getTime();
//            calendar.add(Calendar.DATE,-1);
//            Date date2=calendar.getTime();
//            System.out.println(date2);
//            System.out.println(date1);
////            List<inkscreen>inkscreenList=inkscreenRepository.findAllByTUserAndStartTimeAfterAndEndTimeBefore(tUser,date1,date1);
//            List<inkscreen> inkscreenList = inkscreenRepository.findAllByTUserAndStartTimeBeforeAndEndTimeAfter(tUser,date1,date2);
//            if(inkscreenList.size()==0){
//                return Util.failure(ExceptionEnums.UNFIND_ERROR);
//            }else {
//                return Util.success(inkscreenList);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
////update inkscreen
//    @PutMapping(value = "/inkscreen/update/{id}")
//    public Result updateEvent(@PathVariable("id")Long id, inkscreen inkscreen){
//        try {
//            inkscreen inkscreen1 = inkscreenRepository.findById(id).get();
//            inkscreen1.setEname(inkscreen.getEname());
//            inkscreen1.setContent(inkscreen.getContent());
//            inkscreen1.setLocation(inkscreen.getLocation());
//            inkscreen1.setFinish(inkscreen.isFinish());
//            inkscreen1.setFinishTime(inkscreen.getFinishTime());
//            inkscreen1.setStartTime(inkscreen.getStartTime());
//            inkscreen1.setEndTime(inkscreen.getEndTime());
//            return  Util.success(inkscreenRepository.save(inkscreen1));
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
//        }
//    }
//
//
//
//
//
//}
