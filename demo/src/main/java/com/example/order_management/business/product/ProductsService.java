package com.example.order_management.business.product;

import com.example.order_management.business.product.dto.ProductRequest;
import com.example.order_management.domain.product.Product;
import com.example.order_management.domain.product.ProductMapper;
import com.example.order_management.domain.product.ProductService;
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
