package com.example.customer.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Builder
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
