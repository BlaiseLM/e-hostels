package com.ehotels.app.dao;
import com.ehotels.app.model.*; 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertEmployee(Integer ssn, String chainName, String hotelAddress, String firstName, String lastName, String address, LocalDate registrationDate, String role){ 
        String sql = "INSERT INTO employee (ssn, chain_name, hotel_address, first_name, last_name, address, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ssn, chainName, hotelAddress, firstName, lastName, address, registrationDate);

        String roleSql = "INSERT INTO roles (ssn, chain_name, hotel_address, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(roleSql, ssn, chainName, hotelAddress, role);
    }

    public void deleteEmployee(Integer ssn, String chainName, String hotelAddress){ 
        String sql = "DELETE FROM employee WHERE ssn = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, ssn, hotelAddress, chainName);
    }

    public void updateEmployee(Integer ssn, String chainName, String hotelAddress, String firstName, String lastName, String address, LocalDate registrationDate) { 
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

    public List<Map<String, Object>> searchEmployees(Integer ssn, String chainName, String hotelAddress, String firstName, String lastName, String address, LocalDate registrationDate, String role) {
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (ssn != null) {
            filters.add("ssn = ?");
            params.add(ssn);
        }
        if (chainName != null) {
            filters.add("chain_name = ?");
            params.add(chainName);
        }
        if (hotelAddress != null) {
            filters.add("hotel_address = ?");
            params.add(hotelAddress);
        }
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
        if (registrationDate != null) {
            filters.add("registration_date = ?");
            params.add(registrationDate);
        }
        if (role != null) { 
            filters.add("role = ?"); 
            params.add(role);
        }
        String sql = "SELECT ep.*, ro.role FROM employee ep JOIN roles ro ON ep.ssn = ro.ssn AND ep.chain_name = ro.chain_name AND ep.hotel_address = ro.hotel_address WHERE 1=1";
        if (!filters.isEmpty()) {
            sql += " AND " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }

    public void deleteRole(Integer ssn, String role){ 
        String sql = "DELETE FROM roles WHERE ssn = ? AND role = ?";
        jdbcTemplate.update(sql, ssn, role);
    }
}