package com.example.order_management.domain.order;

import com.example.order_management.business.order.dto.CustomerOrderDto;
import com.example.order_management.domain.order.CustomerOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerOrderMapper {
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "customer.id", target = "customerId")
    CustomerOrderDto toCustomerOrderDto(CustomerOrder customerOrder);

}