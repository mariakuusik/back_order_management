package com.example.order_management.business.order;

import com.example.order_management.business.order.dto.OrderDto;
import com.example.order_management.business.order.dto.CustomerOrderDto;
import com.example.order_management.business.product.dto.UpdateProductQuantityRequest;
import com.example.order_management.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("/order")
    @Operation(summary = "Adds new order to the database",
            description = "Creates lines in order, order_line, order_order_line and customer_order tables")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Order cannot be added",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void createNewOrder(@RequestBody OrderDto orderDto) {
        ordersService.createNewOrder(orderDto);
    }

    @GetMapping("/orders/date")
    @Operation(summary = "Finds orders by date",
            description = "Returns customerId, OrderId, OrderLines (contains productId and productQuantity)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Order cannot be found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<CustomerOrderDto> findAllOrders(@RequestParam LocalDate date) {
        return ordersService.findOrdersByDate(date);
    }

    @GetMapping("/orders/product")
    @Operation(summary = "Finds orders by product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Order cannot be found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<CustomerOrderDto> findOrdersByProduct(@RequestParam Integer productId) {
        return ordersService.findOrdersByProduct(productId);
    }

    @GetMapping("/orders/customer")
    @Operation(summary = "Finds orders by customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Order cannot be found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<CustomerOrderDto> findOrdersByCustomer(@RequestParam Integer customerId) {
        return ordersService.findOrdersByCustomer(customerId);
    }

    @PatchMapping("/orders/order-lines")
    @Operation(summary = "Changes quantity of products in an order line")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Orderline cannot be updated",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void changeProductQuantity(@RequestBody UpdateProductQuantityRequest quantityRequest) {
        ordersService.changeProductQuantity(quantityRequest);
    }
}
