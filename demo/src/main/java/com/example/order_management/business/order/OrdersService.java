package com.example.order_management.business.order;

import com.example.order_management.business.order.orderline.OrderLineDto;
import com.example.order_management.business.order.orderline.OrderLineService;
import com.example.order_management.domain.*;
import com.example.order_management.domain.customer.Customer;
import com.example.order_management.domain.customer.CustomerService;
import com.example.order_management.domain.order.Order;
import com.example.order_management.domain.order.OrderRepository;
import com.example.order_management.domain.order.orderline.OrderLineMapper;
import com.example.order_management.domain.product.Product;
import com.example.order_management.domain.product.ProductService;
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
    private ProductService productService;
    @Resource
    private CustomerOrderService customerOrderService;
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private CustomerOrderMapper customerOrderMapper;
    @Resource
    private OrderRepository orderRepository;

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

    public List<CustomerOrderDto> findOrdersByProduct(Integer productId) {
        List<OrderLine> orderLines = orderLineService.findOrderLinesByProduct(productId);
        List<CustomerOrderDto> customerOrderDtos = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            List<Order> orders = orderService.findOrdersBy(orderLine.getId());
            for (Order order : orders) {
                CustomerOrder customerOrder = customerOrderService.getCustomerOrderId(order.getId());
                if (customerOrder != null) {
                    CustomerOrderDto customerOrderDto = getCustomerOrderDto(order, customerOrder);
                    List<OrderLineDto> orderLineDtos = getOrderLineDtos(orderLine, orderLines);
                    customerOrderDto.setOrderLines(orderLineDtos);
                    customerOrderDtos.add(customerOrderDto);
                } else {
                    throw new RuntimeException("Order doesn't exist");
                }
            }
        }
        return customerOrderDtos;
    }

    public List<CustomerOrderDto> findOrdersByCustomer(Integer customerId) {
        List<CustomerOrder> orders = customerOrderService.findOrders(customerId);
        List<CustomerOrderDto> customerOrderDtos = new ArrayList<>();
        for (CustomerOrder order : orders) {
            CustomerOrder customerOrder = customerOrderService.getCustomerOrderId(order.getId());
            CustomerOrderDto customerOrderDto = getCustomerOrderDto(order.getOrder(), customerOrder);
            List<OrderLineDto> orderLineDtos = getOrderLineDtos(order.getOrder());
            customerOrderDto.setCustomerId(customerId);
            customerOrderDto.setOrderLines(orderLineDtos);
            customerOrderDtos.add(customerOrderDto);
        }
        return customerOrderDtos;
    }

    public void changeProductQuantity(UpdateProductQuantityRequest quantityRequest) {
        Optional<OrderLine> orderLineOptional = orderLineService.findOrderLine(quantityRequest.getOrderLineId());
        if (orderLineOptional.isPresent()) {
            OrderLine orderLine = orderLineOptional.get();
            orderLine.setQuantity(quantityRequest.getQuantity());
            orderLineService.updateOrderLine(orderLine);
        } else {
            throw new RuntimeException("Orderline doesn't exist");
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

    private List<OrderLineDto> getOrderLineDtos(Order order) {
        List<OrderOrderLine> orderLines = orderLineService.findOrderLinesByOrder(order.getId());
        List<OrderLineDto> orderLineDtos = new ArrayList<>();
        for (OrderOrderLine orderLine : orderLines) {
            OrderLineDto orderLineDto = orderLineMapper.toOrderLineDto(orderLine.getOrderLine());
            orderLineDtos.add(orderLineDto);
        }
        return orderLineDtos;
    }

    private List<OrderLineDto> getOrderLineDtos(OrderLine orderLine, List<OrderLine> orderLines) {
        List<OrderLine> orderLinesForOrder = new ArrayList<>();
        orderLinesForOrder.add(orderLine);
        List<OrderLineDto> orderLineDtos = orderLineMapper.toOrderLineDtos(orderLines);
        return orderLineDtos;
    }

    private CustomerOrderDto getCustomerOrderDto(Order order, CustomerOrder customerOrder) {
        Customer customer = customerOrder.getCustomer();
        CustomerOrderDto customerOrderDto = customerOrderMapper.toCustomerOrderDto(customerOrder);
        customerOrderDto.setOrderId(order.getId());
        customerOrderDto.setCustomerId(customer.getId());
        return customerOrderDto;
    }


}
