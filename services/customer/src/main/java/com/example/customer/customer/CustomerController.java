package com.example.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<String> createCustomer( @RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customerId){
        return ResponseEntity.ok((customerService.existsById(customerId)));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String customerId){
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteByID(@PathVariable String customerId){
        customerService.deleteById(customerId);
        return ResponseEntity.accepted().build();
    }
}
