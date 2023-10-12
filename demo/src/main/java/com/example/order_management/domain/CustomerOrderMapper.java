package com.example.order_management.domain;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerOrderMapper {
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "customer.id", target = "customerId")
    CustomerOrderDto toCustomerOrderDto(CustomerOrder customerOrder);

}