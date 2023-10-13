package com.example.order_management.domain;

import com.example.order_management.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    @Transactional
    @Modifying
    @Query("update OrderLine o set o.quantity = ?1")
    int updateQuantityBy(Integer quantity);
    @Transactional
    @Modifying
    @Query("update OrderLine o set o.quantity = ?1 where o.product = ?2")
    int updateQuantityByProduct(Integer quantity, Product product);
    @Transactional
    @Modifying
    @Query("update OrderLine o set o.quantity = ?1 where o.id = ?2")
    int updateQuantityById(Integer quantity, Integer id);



    @Query("select o from OrderLine o where o.product.id = ?1 order by o.product.name")
    List<OrderLine> findOrderLinesBy(Integer productId);

}