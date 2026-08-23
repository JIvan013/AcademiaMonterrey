package com.devmaster.springboot.crudapp.service;

import com.devmaster.springboot.crudapp.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int theId);

    Customer save(Customer theCustomer);

    void deleteById(int theId);

}
