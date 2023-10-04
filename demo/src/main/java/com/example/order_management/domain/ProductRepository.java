package com.example.order_management.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select (count(p) > 0) from Product p where p.skuCode = ?1")
    boolean productExistsBy(Integer skuCode);


}