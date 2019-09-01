package com.example.demo.entity;


import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.swing.text.StyledEditorKit;

@Entity
public class Access extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String username;


    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private TUser tUser;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }
}
