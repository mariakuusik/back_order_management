package com.example.order_management.domain.order;

import com.example.order_management.business.order.dto.OrderDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(source = "orderLines", target = "orderLines")
    @Mapping(source = "id", target = "customerId")
    OrderDto toOrderDto(Order order);
}