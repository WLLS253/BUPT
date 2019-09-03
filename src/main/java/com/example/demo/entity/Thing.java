package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.Date;
import java.util.List;

@Entity
public class Thing  extends BaseEntity{


    @Column(nullable =  false)
    private  String tname;

    private String tContent;


    private  Float tPrice;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    private String category;

    @Column(nullable = false)
    private  boolean state;

    @Column(nullable = false)
    private boolean history;

    @OneToMany(targetEntity = Timage.class)
    private List<Timage> timageList;


    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = UserOrder.class,fetch = FetchType.LAZY)
//    @JoinColumn(name="order_id",referencedColumnName = "id")
//    private UserOrder userOrder;

//    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = UserSelling.class,fetch = FetchType.LAZY)
//    @JoinColumn(name="selling_id",referencedColumnName = "id")
//    private UserSelling userSelling;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }


    public List<Timage> getTimageList() {
        return timageList;
    }

    public void setTimageList(List<Timage> timageList) {
        this.timageList = timageList;
    }

    public Float gettPrice() {
        return tPrice;
    }

    public void settPrice(Float tPrice) {
        this.tPrice = tPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//
//    public UserOrder getUserOrder() {
//        return userOrder;
//    }
//
//    public void setUserOrder(UserOrder userOrder) {
//        this.userOrder = userOrder;
//    }
//
//    public UserSelling getUserSelling() {
//        return userSelling;
//    }
//
//    public void setUserSelling(UserSelling userSelling) {
//        this.userSelling = userSelling;
//    }
//
//    public List<Timage> getTimageList() {
//        return timageList;
//    }

//    public void setTimageList(List<Timage> timageList) {
//        this.timageList = timageList;
//    }
}
