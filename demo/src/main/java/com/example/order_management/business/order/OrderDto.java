package com.example.order_management.business.order;

import com.example.order_management.business.order.orderline.OrderLineDto;
import com.example.order_management.domain.CustomerOrder;
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
public class OrderDto implements Serializable {
    private Integer customerId;
    private List<OrderLineDto> orderLines;
}