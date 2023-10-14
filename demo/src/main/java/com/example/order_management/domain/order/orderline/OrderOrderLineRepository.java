package com.example.order_management.domain.order.orderline;

import com.example.order_management.domain.order.orderline.OrderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderOrderLineRepository extends JpaRepository<OrderOrderLine, Integer> {
    @Query("select o from OrderOrderLine o where o.order.id = ?1")
    List<OrderOrderLine> findOrderLinesBy(Integer orderId);

}