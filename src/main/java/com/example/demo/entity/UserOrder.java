package com.example.demo.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserOrder  extends BaseEntity{

    @Column(unique = true,nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(targetEntity = Thing.class)
    private List<Thing>  thingList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Thing> getThingList() {
        return thingList;
    }

    public void setThingList(List<Thing> thingList) {
        this.thingList = thingList;
    }
}
