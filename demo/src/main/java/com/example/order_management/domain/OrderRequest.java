package com.example.order_management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link CustomerOrder}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest implements Serializable {
    private Integer customerId;
    private List<OrderLineDto> orderLines;
}