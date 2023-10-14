package com.example.order_management.domain.customer;

import com.example.order_management.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Resource
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void validateEmailIsAvailable(String email) {
        boolean customerExists = customerRepository.customerExistsBy(email);
        ValidationService.validateEmailIsAvailable(customerExists);
    }

    public Optional<Customer> findCustomerBy(Integer customerId) {
        return customerRepository.findById(customerId);
    }
}
