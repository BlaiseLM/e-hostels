package com.ehotels.app.controller; 
import com.ehotels.app.dao.*; 
import com.ehotels.app.model.*; 

import java.time.LocalDate;
import java.util.List; 
import java.util.Map; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

@RestController
@RequestMapping("/employees")
public class EmployeeController { 
    private final EmployeeDAO employeeDAO; 

    public EmployeeController(EmployeeDAO employeeDAO){ 
        this.employeeDAO = employeeDAO; 
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) { 
        employeeDAO.insertEmployee(
            employee.ssn(), 
            employee.chainName(), 
            employee.hotelAddress(), 
            employee.firstName(), 
            employee.lastName(),
            employee.address(),  
            employee.registrationDate(), 
            employee.role()
        ); 
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer ssn, @RequestParam String chainName, @RequestParam String hotelAddress) { 
        employeeDAO.deleteEmployee(ssn, chainName, hotelAddress);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        employeeDAO.updateEmployee(
            employee.ssn(), 
            employee.chainName(), 
            employee.hotelAddress(), 
            employee.firstName(), 
            employee.lastName(),
            employee.address(),  
            employee.registrationDate()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> search(
        @RequestParam(required = false) Integer ssn,
        @RequestParam(required = false) String chainName,
        @RequestParam(required = false) String hotelAddress,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String address,
        @RequestParam(required = false) LocalDate registrationDate,
        @RequestParam(required = false) String role
    ) {
        return ResponseEntity.ok(employeeDAO.searchEmployees(ssn, chainName, hotelAddress, firstName, lastName, address, registrationDate, role));
    }

    @DeleteMapping("/role")
    public ResponseEntity<?> deleteRole(@RequestParam Integer ssn, @RequestParam String role) {
        employeeDAO.deleteRole(ssn, role);
        return ResponseEntity.noContent().build();
    }
}