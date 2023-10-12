package com.example.order_management.domain;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerOrderMapper {
    CustomerOrder toEntity(CustomerOrderDto customerOrderDto);

    CustomerOrderDto toDto(CustomerOrder customerOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerOrder partialUpdate(CustomerOrderDto customerOrderDto, @MappingTarget CustomerOrder customerOrder);
}