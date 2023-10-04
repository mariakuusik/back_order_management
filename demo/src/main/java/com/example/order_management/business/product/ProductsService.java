package com.example.order_management.business.product;

import com.example.order_management.domain.Product;
import com.example.order_management.domain.ProductMapper;
import com.example.order_management.domain.ProductRepository;
import com.example.order_management.domain.ProductRequest;
import com.example.order_management.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class ProductsService {

    @Resource
    private ProductMapper productMapper;


    @Resource
    private ProductService productService;



    public void addNewProduct(ProductRequest productRequest) {
        productService.validateSkuCodeIsAvailable(productRequest.getSkuCode());
        Product product = productMapper.toProduct(productRequest);
        productService.saveProduct(product);
    }
}
