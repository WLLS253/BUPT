package com.example.demo.entity;


import javax.persistence.Entity;

@Entity
public class TestJax extends BaseEntity{

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
