package com.example.order_management.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link OrderLine}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto implements Serializable {
    private Integer productId;
    @NotNull
    private Integer productQuantity;
}