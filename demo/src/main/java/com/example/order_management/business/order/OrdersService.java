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
    private OrderLineMapper orderLineMapper;
    @Resource
    private OrderLineService orderLineService;
    @Resource
    OrderRepository orderRepository;
    @Resource
    CustomerService customerService;
    @Resource
    private CustomerOrderService customerOrderService;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private CustomerMapper customerMapper;



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

    //vaja on LIST orders, mille sees on: customerId ja OrderId ja
        //LIST orderLines, mille sees on: productId ja quantity

    public List<CustomerOrderDto> findAllOrdersByDate(LocalDate submissionDate) {
        //otsin üles kuupäeva järgi kõik tellimused
        List<Order> orders = orderService.findOrders(submissionDate);
        List <CustomerOrderDto> customerOrderDtos = new ArrayList<>();
        //käin tellimuste listi loobiga läbi, et saada ligi igale individuaalsele tellimusele
        for (Order order : orders) {
            //otsin orderId järgi üles customerId
            Customer customer = orderService.getCustomerId(order.getId());
            //kuidas customer panna CustomerOrderDto külge? Kas CustomerMapperiga või CustomerOrderMapperiga?
            CustomerOrderDto customerOrderDto = customerOrderMapper.toCustomerOrderDto(customer);
            customerOrderDto.setOrderId(order.getId());
            customerOrderDto.setCustomerId(customer.getId());
            //otsin orderId järgi üles orderLines
            List<OrderLine> orderLines = orderLineService.getOrderLinesBy(order.getId());
            List<OrderLineDto> orderLineDtos = new ArrayList<>();
            //käin OrderLines listi läbi, et saada ligi igale individuaalsele orderLines'ile
            for (OrderLine orderLine : orderLines) {
                OrderLineDto orderLineDto = orderLineMapper.toOrderLineDto(orderLine);
                orderLineDtos.add(orderLineDto);
            }
            customerOrderDto.setOrderLines(orderLineDtos);
            customerOrderDtos.add(customerOrderDto);
        }
        return customerOrderDtos;
    }
}
