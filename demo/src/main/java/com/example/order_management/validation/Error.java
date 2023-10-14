package com.example.order_management.validation;

import lombok.Getter;

@Getter
public enum Error {
    NO_ORDERS_FOUND("Orders were not found", 111),
    NO_ORDERLINES_FOUND("Orderlines were not found", 222),
    EMAIL_NOT_AVAILABLE("User with this e-mail address already exists",333),
    SKU_NOT_AVAILABLE("Product with this SKU code already exists", 444);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
