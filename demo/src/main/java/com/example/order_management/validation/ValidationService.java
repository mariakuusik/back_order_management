package com.example.order_management.validation;

import com.example.order_management.infrastructure.exception.BusinessException;

public class ValidationService {
    public static void validateEmailIsAvailable(boolean customerExists) {
        if (customerExists) {
            throw new BusinessException("This email is already in use", 111);
        }
    }

    public static void validateSkuCodeIsAvailable(Boolean productExists) {
        if (productExists) {
            throw  new BusinessException("This product already exists", 222);
        }
    }
}
