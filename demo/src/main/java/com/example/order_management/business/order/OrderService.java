package com.example.order_management.business.order;

import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerRepository;
import com.example.order_management.domain.order.Order;
import com.example.order_management.domain.order.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    public List<Order> findOrders(LocalDate date) {
        return orderRepository.findOrdersBy(date);
    }
}
