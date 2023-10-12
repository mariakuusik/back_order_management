package com.example.order_management.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.submissionDate = ?1 order by o.submissionDate")
    List<Order> findOrdersBy(LocalDate date);

    @Query("select o from Order o inner join o.orderLines orderLines where orderLines.id = ?1")
    List<Order> findOrdersByOrderLineId(Integer orderLineId);






}