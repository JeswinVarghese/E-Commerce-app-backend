package com.example.customer.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
