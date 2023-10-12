package com.example.order_management.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {


    List<OrderLine> findOrderLinesById(Integer integer);
}