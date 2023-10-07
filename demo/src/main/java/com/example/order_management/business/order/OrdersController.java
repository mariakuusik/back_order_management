package com.example.order_management.business.order;


import com.example.order_management.domain.OrderRepository;
import com.example.order_management.domain.OrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/order")
    @Operation(summary = "Adds new order to the database",
    description = "Creates lines in order, order_line, order_order_line and customer_order tables")
    public void createNewOrder(@RequestBody OrderRequest orderRequest){
        ordersService.createNewOrder(orderRequest);
    }
}
