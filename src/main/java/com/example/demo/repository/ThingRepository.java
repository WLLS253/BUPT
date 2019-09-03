package com.example.demo.repository;

import com.example.demo.entity.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing,Long> {
}
