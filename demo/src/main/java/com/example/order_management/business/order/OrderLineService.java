package com.example.order_management.business.order;

import com.example.order_management.domain.OrderLine;
import com.example.order_management.domain.OrderLineRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    @Resource
    private OrderLineRepository orderLineRepository;


    public void createOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }
}
