package com.example.order_management.business.product.dto;

import com.example.order_management.domain.order.orderline.OrderLine;
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
public class UpdateProductQuantityRequest implements Serializable {
    private Integer orderLineId;
    private Integer productId;
    @NotNull
    private Integer quantity;
    private Integer orderId;
    private Integer customerId;
}