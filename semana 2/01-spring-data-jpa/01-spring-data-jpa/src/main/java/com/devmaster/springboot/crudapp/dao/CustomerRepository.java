package com.devmaster.springboot.crudapp.dao;

import com.devmaster.springboot.crudapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
