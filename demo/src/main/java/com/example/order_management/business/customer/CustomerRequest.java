package com.example.order_management.business.customer;

import com.example.order_management.domain.customer.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable {
    @NotNull
    private Integer registrationCode;
    @NotNull
    @Size(max = 255)
    private String fullName;
    @NotNull
    @Size(max = 255)
    private String email;
    @NotNull
    private Integer telephone;
}