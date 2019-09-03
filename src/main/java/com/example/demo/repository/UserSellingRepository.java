package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserSelling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSellingRepository extends JpaRepository<UserSelling,Long> {

    public List<UserSelling>findAllByUser(User user);
}
