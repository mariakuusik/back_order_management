package com.example.order_management.business.customer;


import com.example.order_management.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomersController {

    @Resource
    private CustomersService customersService;

    @PostMapping("/customer")
    @Operation(summary = "Adds new customer to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "This email is already in use",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    private void AddCustomer(@RequestBody CustomerRequest customerRequest){
        customersService.addCustomer(customerRequest);
    }


}
