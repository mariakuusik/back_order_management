package com.example.order_management.business.order;

import com.example.order_management.domain.*;
import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerRepository;
import com.example.order_management.domain.customer.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrdersService {
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private OrderLineService orderLineService;
    @Resource
    OrderRepository orderRepository;
    @Resource
    CustomerService customerService;
    @Resource
    private CustomerOrderService customerOrderService;

    public void createNewOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setSubmissionDate(LocalDate.now());
        orderRepository.save(order);
        Optional<Customer> customerOptional = customerService.findCustomerBy(orderRequest.getCustomerId());
        if (customerOptional.isPresent()) {
            createNewCustomerOrder(customerOptional, order);
            createOrderLines(orderRequest, order);
        } else {
            throw new RuntimeException("Customer doesn't exist");
        };
    }

    private void createNewCustomerOrder(Optional<Customer> customerOptional, Order order) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customerOptional.get());
        customerOrder.setOrder(order);
        customerOrderService.saveCustomerOrder(customerOrder);
    }

    private void createOrderLines(OrderRequest orderRequest, Order order) {
        for (OrderLineDto orderLineDto : orderRequest.getOrderLines()) {
            OrderLine orderLine = orderLineMapper.toOrderLine(orderLineDto);
            order.getOrderLines().add(orderLine);
            orderLineService.createOrderLine(orderLine);
        }
    }
}
