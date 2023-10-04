package com.example.order_management.domain;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "productName", target = "name")
    Product toProduct(ProductRequest productRequest);

}