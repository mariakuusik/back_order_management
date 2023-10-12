package com.example.order_management.business.product;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Resource
    private ProductsService productsService;

    @PostMapping("/product")
    @Operation(summary = "Adds new product to the database")
    public void addNewProduct(@RequestBody ProductRequest productRequest){
        productsService.addNewProduct(productRequest);
    }
}
