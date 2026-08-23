package com.devmaster.springboot.crudapp.rest;

import tools.jackson.databind.json.JsonMapper;
import com.devmaster.springboot.crudapp.entity.Customer;
import com.devmaster.springboot.crudapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private CustomerService customerService;

    private JsonMapper jsonMapper;

    @Autowired
    public CustomerRestController(CustomerService theCustomerService, JsonMapper theJsonMapper) {
        customerService = theCustomerService;
        jsonMapper = theJsonMapper;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer theCustomer = customerService.findById(customerId);

        if (theCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        return theCustomer;
    }


    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {


        theCustomer.setId(0);

        Customer dbCustomer = customerService.save(theCustomer);

        return dbCustomer;
    }


    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        Customer dbCustomer = customerService.save(theCustomer);

        return dbCustomer;
    }


    @PatchMapping("/customers/{customerId}")
    public Customer patchCustomer(@PathVariable int customerId,
            @RequestBody Map<String, Object> patchPayload) {

        Customer tempCustomer = customerService.findById(customerId);

        if (tempCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException(
                    "Customer id cannot be modified. Remove 'id' from request body.");
        }

        Customer patchedCustomer = jsonMapper.updateValue(tempCustomer, patchPayload);

        Customer dbCustomer = customerService.save(patchedCustomer);

        return dbCustomer;
    }


    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer tempCustomer = customerService.findById(customerId);

        // throw exception if null

        if (tempCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        customerService.deleteById(customerId);

        return "Deleted customer id - " + customerId;
    }

}
