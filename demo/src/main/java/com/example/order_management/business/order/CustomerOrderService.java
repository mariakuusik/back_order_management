package com.example.order_management.business.order;

import com.example.order_management.domain.CustomerOrder;
import com.example.order_management.domain.CustomerOrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {
    @Resource
    private CustomerOrderRepository customerOrderRepository;

    public void saveCustomerOrder(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }
    public CustomerOrder getCustomerOrderId(Integer orderId) {
        return customerOrderRepository.findCustomerOrderBy(orderId);
    }

    public List<CustomerOrder> findOrders(Integer customerId) {
        return customerOrderRepository.findCustomerOrdersBy(customerId);
    }
}
