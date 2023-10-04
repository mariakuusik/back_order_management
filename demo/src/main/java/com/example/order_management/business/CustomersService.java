package com.example.order_management.business;

import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerMapper;
import com.example.order_management.domain.customer.CustomerRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerMapper customerMapper;

    public void addCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerService.validateEmailIsAvailable(customerRequest.getEmail());
        customerService.saveCustomer(customer);
    }
}
