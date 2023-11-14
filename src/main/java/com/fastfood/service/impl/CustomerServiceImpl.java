package com.fastfood.service.impl;

import com.fastfood.model.Customer;
import com.fastfood.repository.CustomerRepository;
import com.fastfood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }

    @Override
    public Customer findCustomerById(Long cpf) {
        return null;
    }
}
