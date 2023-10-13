package com.example.order_management.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    @Query("select c from CustomerOrder c where c.order.id = ?1")
    CustomerOrder findCustomerOrderBy(Integer orderId);

    @Query("select c from CustomerOrder c where c.customer.id = ?1 order by c.customer.fullName")
    List<CustomerOrder> findCustomerOrdersBy(Integer customerId);


}