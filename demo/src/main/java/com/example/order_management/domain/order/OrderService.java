package com.example.order_management.domain.order;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    public List<Order> findOrdersByDate(LocalDate date) {
        return orderRepository.findOrdersBy(date);
    }

    public List<Order> findOrdersByOrderLine(Integer orderLineId) {
        return orderRepository.findOrdersByOrderLineId(orderLineId);
    }
}
