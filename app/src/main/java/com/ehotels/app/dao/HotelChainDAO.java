package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class HotelChainDAO {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final JdbcTemplate jdbcTemplate;

    public HotelChainDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertChain(String name, String centralOfficeAddress){ 
        String sql = "INSERT INTO hotel_chain (name, central_office_address) VALUES (?, ?)"; 
        jdbcTemplate.update(sql, name, centralOfficeAddress); 
    }

    public void updateChainAddress(String name, String newAddress){
        String sql = "UPDATE hotel_chain SET central_office_address = ? WHERE name = ?"; 
        jdbcTemplate.update(sql, newAddress, name); 
    }

    public void deleteChain(String name){ 
        String sql = "DELETE FROM hotel_chain WHERE name = ?"; 
        jdbcTemplate.update(sql, name); 
    }

    public void insertChainPhone(String chainName, String phoneNumber){ 
        String sql = "INSERT INTO chain_phone_numbers (name, phone_number) VALUES (?, ?)";
        jdbcTemplate.update(sql, chainName, phoneNumber); 
    }

    public void deleteChainPhone(String chainName, String phoneNumber){ 
        String sql = "DELETE FROM chain_phone_numbers WHERE name = ? AND phone_number = ?"; 
        jdbcTemplate.update(sql, chainName, phoneNumber); 
    }

    public void insertChainEmail(String chainName, String email){ 
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        String sql = "INSERT INTO chain_emails (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, chainName, email); 
    }

    public void deleteChainEmail(String chainName, String email){ 
        String sql = "DELETE FROM chain_emails WHERE name = ? AND email = ?"; 
        jdbcTemplate.update(sql, chainName, email); 
    }

    public List<Map<String, Object>> searchHotelChain(String chainName, String phoneNumber, String email) {
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (chainName != null) {
            filters.add("hc.name = ?");
            params.add(chainName);
        }
        if (phoneNumber != null) {
            filters.add("cp.phone_number = ?");
            params.add(phoneNumber);
        }
        if (email != null) {
            filters.add("ce.email = ?");
            params.add(email);
        }
        String sql = "SELECT hc.name, cp.phone_number, ce.email FROM hotel_chain hc " +
                     "JOIN chain_emails ce ON hc.name = ce.name " +
                     "JOIN chain_phone_numbers cp ON hc.name = cp.name";
        if (!filters.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    
}