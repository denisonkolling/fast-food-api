package com.fastfood.controller;

import com.fastfood.dto.CustomerRequest;
import com.fastfood.dto.CustomerResponse;
import com.fastfood.model.Customer;
import com.fastfood.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerResponse r = mapper.map(customer, CustomerResponse.class);
            response.add(r);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustome(@RequestBody @Valid CustomerRequest request) {
        var customer = mapper.map(request,Customer.class);
        customer = customerService.createCustomer(customer);
        var response = mapper.map(customer, CustomerResponse.class);
        return ResponseEntity.created(URI.create(customer.getCpf().toString())).body(response);
    }

}
