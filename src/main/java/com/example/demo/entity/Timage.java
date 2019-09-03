package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Timage extends BaseEntity {

    @Column(nullable = true)
    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = Thing.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "thing_id",referencedColumnName = "id")
    private Thing thing;



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonBackReference
    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }
}
