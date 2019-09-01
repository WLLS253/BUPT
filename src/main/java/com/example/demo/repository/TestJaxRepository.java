package com.example.demo.repository;

import com.example.demo.entity.TestJax;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJaxRepository extends JpaRepository<TestJax,Long> {
}
