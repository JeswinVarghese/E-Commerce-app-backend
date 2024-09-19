package com.example.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
){
}
