package com.example.order_management.domain.customer;

import com.example.order_management.business.customer.CustomerRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    Customer toCustomer(CustomerRequest customerRequest);
}