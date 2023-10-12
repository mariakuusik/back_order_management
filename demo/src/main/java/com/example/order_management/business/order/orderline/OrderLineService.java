package com.example.order_management.business.order.orderline;

import com.example.order_management.domain.OrderLine;
import com.example.order_management.domain.OrderLineRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {

    @Resource
    private OrderLineRepository orderLineRepository;


    public void createOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }

    public List<OrderLine> getOrderLinesBy(Integer orderId) {
        return orderLineRepository.findOrderLinesById(orderId);
    }

}
