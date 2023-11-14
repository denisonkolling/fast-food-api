package com.fastfood.service;

import com.fastfood.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Customer findCustomerById(Long cpf);

    Customer editCustomer(Customer customer);

    void deleteCustomer(Long cpf);

}
