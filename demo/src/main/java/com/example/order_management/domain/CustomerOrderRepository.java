package com.example.order_management.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    @Query("select c from CustomerOrder c where c.order.id = ?1")
    CustomerOrder findCustomerOrderBy(Integer id);
}