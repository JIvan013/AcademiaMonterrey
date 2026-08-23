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

    // expose "/customers" and return a list of customers
    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    // add mapping for GET /customers/{customerId}
    //
    // El customerId ahora es String: un ObjectId de MongoDB, no un entero.

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {

        Customer theCustomer = customerService.findById(customerId);

        if (theCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        return theCustomer;
    }

    // add mapping for POST /customers - add new customer

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        // also just in case they pass an id in JSON ... set id to null
        // this is to force a save of new item ... instead of update
        //
        // En JPA esto era setId(0). En MongoDB el equivalente es null: si el id
        // viene nulo se inserta un documento nuevo, y si viene con valor se
        // REEMPLAZA el documento que ya existía con ese id.

        theCustomer.setId(null);

        Customer dbCustomer = customerService.save(theCustomer);

        return dbCustomer;
    }

    // add mapping for PUT /customers - update existing customer

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        Customer dbCustomer = customerService.save(theCustomer);

        return dbCustomer;
    }

    // add mapping for PATCH /customers/{customerId} - patch customer ... partial
    // update

    @PatchMapping("/customers/{customerId}")
    public Customer patchCustomer(@PathVariable String customerId,
            @RequestBody Map<String, Object> patchPayload) {

        // Step 1: Retrieve the existing customer from database
        Customer tempCustomer = customerService.findById(customerId);

        if (tempCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        // Step 2: Security check - prevent ID modifications
        // The ID should never change, so reject any attempts to modify it
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException(
                    "Customer id cannot be modified. Remove 'id' from request body.");
        }

        // Step 3: Apply the partial update
        // This creates a NEW customer object with the updates applied
        Customer patchedCustomer = jsonMapper.updateValue(tempCustomer, patchPayload);

        // Step 4: Save the updated customer to database and return it
        Customer dbCustomer = customerService.save(patchedCustomer);

        return dbCustomer;
    }

    // add mapping for DELETE /customers/{customerId} - delete customer

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {

        Customer tempCustomer = customerService.findById(customerId);

        // throw exception if null

        if (tempCustomer == null) {
            throw new RuntimeException("Customer id not found - " + customerId);
        }

        customerService.deleteById(customerId);

        return "Deleted customer id - " + customerId;
    }

}
