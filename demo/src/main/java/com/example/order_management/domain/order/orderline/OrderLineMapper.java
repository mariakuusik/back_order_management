package com.example.order_management.domain.order.orderline;

import com.example.order_management.domain.OrderLine;
import com.example.order_management.business.order.orderline.OrderLineDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderLineMapper {
    @Mapping(source = "productQuantity", target = "quantity")
    @Mapping(source = "productId", target = "product.id")
    OrderLine toOrderLine(OrderLineDto orderLineDto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "quantity", target = "productQuantity")
    OrderLineDto toOrderLineDto(OrderLine orderLine);

    List<OrderLineDto> toOrderLineDtos(List<OrderLine>orderLines);

}

