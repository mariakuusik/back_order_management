package com.example.order_management.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    @Query("select o from OrderLine o where o.product.id = ?1 order by o.product.name")
    List<OrderLine> findOrderLinesBy(Integer productId);

}