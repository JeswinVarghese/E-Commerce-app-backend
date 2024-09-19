package com.example.customer.customer;

import com.example.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;


    public String createCustomer(CustomerRequest request) {
       Customer customer = repository.save(customerMapper.mapToCustomer(request));
       return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer not found with ID %s",request.id())
        ));

        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
        repository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(customerMapper :: mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(customerMapper :: mapToCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found with ID %s",customerId)));
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
