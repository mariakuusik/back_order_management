package com.example.order_management.business.product;

import com.example.order_management.domain.Product;
import com.example.order_management.domain.ProductRepository;
import com.example.order_management.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


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
