package com.example.order_management.business.product.dto;

import com.example.order_management.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {
    @NotNull
    @Size(max = 255)
    private String productName;
    @NotNull
    private Integer skuCode;
    @NotNull
    private BigDecimal unitPrice;
}