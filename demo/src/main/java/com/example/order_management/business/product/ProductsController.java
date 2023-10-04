package com.example.order_management.business.product;

import com.example.order_management.domain.ProductRequest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Resource
    private ProductsService productsService;

    @PostMapping("/product")
    public void addNewProduct(@RequestBody ProductRequest productRequest){
        productsService.addNewProduct(productRequest);
    }
}
