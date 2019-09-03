package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.OrderForm;

import java.util.Date;
import java.util.List;


public interface OrderFormRepository extends JpaRepository<OrderForm,Long> {

    public List<OrderForm>findAllByDateBeforeAndDateAfter(Date date1,Date date2);
}
