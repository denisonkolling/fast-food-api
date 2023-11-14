package com.fastfood.service.impl;

import com.fastfood.model.Customer;
import com.fastfood.repository.CustomerRepository;
import com.fastfood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long cpf) {
        return customerRepository.findById(cpf).orElseThrow(() -> new IllegalArgumentException("CPF not found"));
    }

    @Override
    public Customer editCustomer(Customer customer) {
        Customer customerDB = customerRepository.findById(customer.getCpf()).orElseThrow(() -> new IllegalArgumentException("CPF not found"));
        mapper.map(customer, customerDB);
        customerDB = customerRepository.save(customerDB);
        return customerDB;
    }

    @Override
    public void deleteCustomer(Long cpf) {
        boolean existsCustomer = customerRepository.existsById(cpf);
        if (!existsCustomer) {
            throw new IllegalArgumentException("CPF not found");
        }
        customerRepository.deleteById(cpf);
    }


}
