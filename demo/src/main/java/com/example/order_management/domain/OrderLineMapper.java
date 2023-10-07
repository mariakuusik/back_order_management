package com.example.order_management.domain;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderLineMapper {
    @Mapping(source = "productQuantity", target = "quantity")
    @Mapping(source = "productId", target = "product.id")
    OrderLine toOrderLine(OrderLineDto orderLineDto);
}