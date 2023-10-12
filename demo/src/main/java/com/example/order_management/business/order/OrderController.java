package com.example.order_management.business.order;

import com.example.order_management.domain.CustomerOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("/order")
    @Operation(summary = "Adds new order to the database",
    description = "Creates lines in order, order_line, order_order_line and customer_order tables")
    public void createNewOrder(@RequestBody OrderDto orderDto){
        ordersService.createNewOrder(orderDto);
    }

    @GetMapping("/orders")
    @Operation(summary = "Finds all orders by date")
    public List<CustomerOrderDto> findAllOrders(@RequestParam LocalDate date) {
        return ordersService.findAllOrdersByDate(date);
    }
}
