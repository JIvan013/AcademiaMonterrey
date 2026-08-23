package com.devmaster.springboot.crudapp.service;

import com.devmaster.springboot.crudapp.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(String theId);

    Customer save(Customer theCustomer);

    void deleteById(String theId);

}
