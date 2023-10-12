package com.example.order_management.business.order.orderline;

import com.example.order_management.domain.OrderLine;
import com.example.order_management.domain.OrderLineRepository;
import com.example.order_management.domain.OrderOrderLine;
import com.example.order_management.domain.OrderOrderLineRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {


    @Resource
    private OrderLineRepository orderLineRepository;

    @Resource
    private  OrderOrderLineRepository orderOrderLineRepository;



    public void createOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }

    public List<OrderOrderLine> getOrderLinesBy(Integer orderId) {
        List<OrderOrderLine> orderLines = orderOrderLineRepository.findOrderLinesBy(orderId);
        return orderLines;
    }



}
