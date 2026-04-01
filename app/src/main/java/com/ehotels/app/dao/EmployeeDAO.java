package com.ehotels.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertEmployee(String ssn, String chainName, String hotelAddress, String firstName, String lastName, String address, LocalDate registrationDate){ 
        String sql = "INSERT INTO employee (ssn, chain_name, hotel_address, first_name, last_name, address, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ssn, chainName, hotelAddress, firstName, lastName, address, registrationDate);
    }

    public void deleteEmployee(String ssn, String chainName, String hotelAddress){ 
        String sql = "DELETE FROM employee WHERE ssn = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, ssn, hotelAddress, chainName);
    }

    public void updateEmployee(String ssn, String chainName, String hotelAddress, String firstName, String lastName, String address, LocalDate registrationDate){ 
        List<String> assignments = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (firstName != null) {
            assignments.add("first_name = ?");
            params.add(firstName);
        }
        if (lastName != null) {
            assignments.add("last_name = ?");
            params.add(lastName);
        }
        if (address != null) {
            assignments.add("address = ?");
            params.add(address);
        }
        if (registrationDate != null) {
            assignments.add("registration_date = ?");
            params.add(registrationDate);
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update for employee ssn=" + ssn + " hotel_address=" + hotelAddress + " chain_name=" + chainName);
        }
        String sql = "UPDATE employee SET " + String.join(", ", assignments) + " WHERE ssn = ? AND hotel_address = ? AND chain_name = ?";
        params.add(ssn);
        params.add(hotelAddress);
        params.add(chainName);
        jdbcTemplate.update(sql, params.toArray());
    }

    // waiting for query
    public void insertRole(/* employeeId, role */){ 

    }

    // waiting for query
    public void deleteRole(/* employeeId, role */){ 
        
    }

}
