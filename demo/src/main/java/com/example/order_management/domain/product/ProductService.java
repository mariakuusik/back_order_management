package com.example.order_management.domain.product;

import com.example.order_management.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {
    @Resource
    ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void validateSkuCodeIsAvailable(Integer skuCode) {
        boolean productExists = productRepository.productExistsBy(skuCode);
        ValidationService.validateSkuCodeIsAvailable(productExists);
    }
}
