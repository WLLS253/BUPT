package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class UserSelling extends BaseEntity {

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private  User user;

    @OneToMany(targetEntity = Thing.class)
    private List<Thing> thingList;

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
