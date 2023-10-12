package com.example.order_management.domain;

import com.example.order_management.business.order.orderline.OrderLineDto;
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
public class CustomerOrderDto implements Serializable {
    private Integer customerId;
    private Integer orderId;
    private List<OrderLineDto> orderLines;
}