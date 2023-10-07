package com.example.order_management.domain.product;

import com.example.order_management.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select (count(p) > 0) from Product p where p.skuCode = ?1")
    boolean productExistsBy(Integer skuCode);

    @Query("select p from Product p where p.skuCode = ?1")
    Product findBySkuCode(Integer skuCode);



}