package com.example.demo.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Schedule extends BaseEntity{


    @Column(unique = true,nullable = false)
    @Temporal(TemporalType.DATE)
    private Date  date;



    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = TUser.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "TuserSched")
    private  TUser tUser;



    @OneToMany(targetEntity = inkscreen.class)
    private List<inkscreen> inkscreenList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public List<inkscreen> getInkscreenList() {
        return inkscreenList;
    }

    public void setInkscreenList(List<inkscreen> inkscreenList) {
        this.inkscreenList = inkscreenList;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date=" + date +
                ", tUser=" + tUser +
                ", inkscreenList=" + inkscreenList +
                ", id=" + id +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
