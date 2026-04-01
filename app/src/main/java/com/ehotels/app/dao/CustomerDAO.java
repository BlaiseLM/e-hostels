package com.ehotels.app.dao; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO { 

    private final JdbcTemplate jdbcTemplate; 

    public CustomerDAO(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    }

    public void insertCustomer(String firstName, String lastName, String address, String idType, String idValue, LocalDate registrationDate){ 
        String sql = "INSERT INTO customer (first_name, last_name, address, id_type, id_value, registration_date) VALUES (?, ?, ?, ?, ?, ?)"; 
        jdbcTemplate.update(sql, firstName, lastName, address, idType, idValue, registrationDate); 
    }

    public void deleteCustomer(int id){ 
        String sql = "DELETE FROM customer WHERE id = ?"; 
        jdbcTemplate.update(sql, id); 
    }

    public void updateCustomer(int id, String firstName, String lastName, String address, String idType, String idValue, LocalDate registrationDate) {
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
        if (idType != null) {
            assignments.add("id_type = ?");
            params.add(idType);
        }
        if (idValue != null) {
            assignments.add("id_value = ?");
            params.add(idValue);
        }
        if (registrationDate != null) {
            assignments.add("registration_date = ?");
            params.add(registrationDate);
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update for customer id=" + id);
        }
        String sql = "UPDATE customer SET " + String.join(", ", assignments) + " WHERE id = ?";
        params.add(id);
        jdbcTemplate.update(sql, params.toArray());
    }
}