package com.ehotels.app.dao; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO { 

    private final JdbcTemplate jdbcTemplate; 

    public CustomerDAO(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    }

    public int insertCustomer(String firstName, String lastName, String address, String idType, String idValue, LocalDate registrationDate){ 
        String sql = "INSERT INTO customer (first_name, last_name, address, id_type, id_value, registration_date) VALUES (?, ?, ?, ?, ?, ?) RETURNING id"; 
        return jdbcTemplate.queryForObject(sql, Integer.class, firstName, lastName, address, idType, idValue, registrationDate); 
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

    public List<Map<String, Object>> searchCustomers(String firstName, String lastName, String address, String idType, String idValue, LocalDate registrationDate) {
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (firstName != null) {
            filters.add("first_name = ?");
            params.add(firstName);
        }
        if (lastName != null) {
            filters.add("last_name = ?");
            params.add(lastName);
        }
        if (address != null) {
            filters.add("address = ?");
            params.add(address);
        }
        if (idType != null) {
            filters.add("id_type = ?");
            params.add(idType);
        }
        if (idValue != null) {
            filters.add("id_value = ?");
            params.add(idValue);
        }
        if (registrationDate != null) {
            filters.add("registration_date = ?");
            params.add(registrationDate);
        }
        String sql = "SELECT * FROM customer";
        if (!filters.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }
}