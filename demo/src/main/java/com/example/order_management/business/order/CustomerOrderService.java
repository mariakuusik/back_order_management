package com.example.order_management.business.order;

import com.example.order_management.domain.CustomerOrder;
import com.example.order_management.domain.CustomerOrderRepository;
import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {
    @Resource
    private CustomerOrderRepository customerOrderRepository;

    public void saveCustomerOrder(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }
}
