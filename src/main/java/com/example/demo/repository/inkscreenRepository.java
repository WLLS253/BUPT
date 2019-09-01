package com.example.demo.repository;

import com.example.demo.entity.inkscreen;
import com.example.demo.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface inkscreenRepository extends JpaRepository<inkscreen,Long> {

    public  List<inkscreen>findAllByTUser(TUser tUser);

    public  List<inkscreen>findAllByTUserAndStartTimeAfterAndEndTimeBefore(TUser tUser, Date start, Date end);

    public  List<inkscreen>findAllByTUserAndStartTimeBeforeAndEndTimeAfter(TUser tUser, Date start, Date end);

    public  List<inkscreen>findAllByLocation(String location);

    public  List<inkscreen>findAllByTUserAndFinish(TUser tUser,boolean finish);



}
