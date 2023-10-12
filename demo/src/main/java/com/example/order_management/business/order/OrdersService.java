package com.example.order_management.business.order;

import com.example.order_management.business.order.orderline.OrderLineDto;
import com.example.order_management.business.order.orderline.OrderLineService;
import com.example.order_management.domain.*;
import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerMapper;
import com.example.order_management.domain.customer.CustomerService;
import com.example.order_management.domain.order.Order;
import com.example.order_management.domain.order.OrderMapper;
import com.example.order_management.domain.order.OrderRepository;
import com.example.order_management.domain.order.orderline.OrderLineMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Resource
    CustomerService customerService;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderLineService orderLineService;
    @Resource
    private CustomerOrderService customerOrderService;
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private CustomerOrderMapper customerOrderMapper;
    @Resource
    OrderRepository orderRepository;

    public void createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setSubmissionDate(LocalDate.now());
        orderRepository.save(order);
        Optional<Customer> customerOptional = customerService.findCustomerBy(orderDto.getCustomerId());
        if (customerOptional.isPresent()) {
            createNewOrder(customerOptional, order);
            createOrderLines(orderDto, order);
        } else {
            throw new RuntimeException("Customer doesn't exist");
        }
    }
    private void createNewOrder(Optional<Customer> customerOptional, Order order) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customerOptional.get());
        customerOrder.setOrder(order);
        customerOrderService.saveCustomerOrder(customerOrder);
    }
    private void createOrderLines(OrderDto orderDto, Order order) {
        for (OrderLineDto orderLineDto : orderDto.getOrderLines()) {
            OrderLine orderLine = orderLineMapper.toOrderLine(orderLineDto);
            order.getOrderLines().add(orderLine);
            orderLineService.createOrderLine(orderLine);
        }
    }
    public List<CustomerOrderDto> findAllOrdersByDate(LocalDate submissionDate) {
        List<Order> orders = orderService.findOrders(submissionDate);
        List<CustomerOrderDto> customerOrderDtos = new ArrayList<>();
        for (Order order : orders) {
            CustomerOrder customerOrder = customerOrderService.getCustomerOrderId(order.getId());
            if (customerOrder != null) {
                CustomerOrderDto customerOrderDto = getCustomerOrderDto(order, customerOrder);
                List<OrderLineDto> orderLineDtos = getOrderLineDtos(order);
                customerOrderDto.setOrderLines(orderLineDtos);
                customerOrderDtos.add(customerOrderDto);
            } else {
                throw new RuntimeException("Order doesn't exist");
            }
        }
        return customerOrderDtos;
    }
    private CustomerOrderDto getCustomerOrderDto(Order order, CustomerOrder customerOrder) {
        Customer customer = customerOrder.getCustomer();
        CustomerOrderDto customerOrderDto = customerOrderMapper.toCustomerOrderDto(customerOrder);
        customerOrderDto.setOrderId(order.getId());
        customerOrderDto.setCustomerId(customer.getId());
        return customerOrderDto;
    }
    private List<OrderLineDto> getOrderLineDtos(Order order) {
        List<OrderOrderLine> orderLines = orderLineService.getOrderLinesBy(order.getId());
        List<OrderLineDto> orderLineDtos = new ArrayList<>();
        for (OrderOrderLine orderLine : orderLines) {
            OrderLineDto orderLineDto = orderLineMapper.toOrderLineDto(orderLine.getOrderLine());
            orderLineDtos.add(orderLineDto);
        }
        return orderLineDtos;
    }
}
