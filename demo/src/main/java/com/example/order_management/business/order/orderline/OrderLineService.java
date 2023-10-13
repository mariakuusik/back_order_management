package com.example.order_management.business.order.orderline;

import com.example.order_management.domain.OrderLine;
import com.example.order_management.domain.OrderLineRepository;
import com.example.order_management.domain.OrderOrderLine;
import com.example.order_management.domain.OrderOrderLineRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {
    @Resource
    private OrderLineRepository orderLineRepository;
    @Resource
    private  OrderOrderLineRepository orderOrderLineRepository;

    public void createOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }
    public List<OrderOrderLine> findOrderLinesByOrder(Integer orderId) {
        List<OrderOrderLine> orderLines = orderOrderLineRepository.findOrderLinesBy(orderId);
        return orderLines;
    }
    public List<OrderLine> findOrderLinesByProduct(Integer productId) {
        return orderLineRepository.findOrderLinesBy(productId);
    }

    public Optional<OrderLine> findOrderLine(Integer orderLineId) {
        return orderLineRepository.findById(orderLineId);
    }

    public void updateOrderLine(OrderLine orderLine) {
        orderLineRepository.save(orderLine);
    }
}
