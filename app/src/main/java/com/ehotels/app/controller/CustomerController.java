package com.ehotels.app.controller; 
import com.ehotels.app.dao.*;
import com.ehotels.app.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 


@RestController
@RequestMapping("/customers")
public class CustomerController { 
    private final CustomerDAO customerDAO; 

    public CustomerController(CustomerDAO customerDAO) { 
        this.customerDAO = customerDAO; 
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) { 
        customerDAO.insertCustomer(
            customer.firstName(), 
            customer.lastName(), 
            customer.address(), 
            customer.idType(), 
            customer.idValue(), 
            customer.registrationDate()
        ); 
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) { 
        customerDAO.deleteCustomer(id);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Customer customer) {
        customerDAO.updateCustomer(
            id,
            customer.firstName(),
            customer.lastName(),
            customer.address(),
            customer.idType(),
            customer.idValue(),
            customer.registrationDate()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> search(
        @RequestParam(required = false) String firstName, 
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String address,
        @RequestParam(required = false) String idType,
        @RequestParam(required = false) String idValue,
        @RequestParam(required = false) LocalDate registrationDate
    ) {
        return ResponseEntity.ok(customerDAO.searchCustomers(firstName, lastName, address, idType, idValue, registrationDate));
    }
}