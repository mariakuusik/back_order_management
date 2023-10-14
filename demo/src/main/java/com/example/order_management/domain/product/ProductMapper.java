package com.example.order_management.domain.product;

import com.example.order_management.business.product.dto.ProductRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "productName", target = "name")
    Product toProduct(ProductRequest productRequest);

}