package com.example.order_management.validation;

import com.example.order_management.infrastructure.exception.BusinessException;

import static com.example.order_management.validation.Error.*;

public class ValidationService {
    public static void validateEmailIsAvailable(boolean customerExists) {
        if (customerExists) {
            throw new BusinessException(EMAIL_NOT_AVAILABLE.getMessage(), EMAIL_NOT_AVAILABLE.getErrorCode());
        }
    }

    public static void validateSkuCodeIsAvailable(Boolean productExists) {
        if (productExists) {
            throw new BusinessException(SKU_NOT_AVAILABLE.getMessage(), SKU_NOT_AVAILABLE.getErrorCode());
        }
    }
}
