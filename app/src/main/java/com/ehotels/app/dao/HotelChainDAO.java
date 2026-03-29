package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotelChainDAO {

    private final JdbcTemplate jdbcTemplate;

    public HotelChainDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertChain(String name, String centralOfficeAddress){ 
        String sql = "INSERT INTO hotel_chain (name, central_office_name) VALUES (?, ?)"; 
        jdbcTemplate.update(sql, name, centralOfficeAddress); 
    }

    public void updateChainAddress(String name, String newAddress){
        String sql = "UPDATE hotel_chain SET address = ? WHERE name = ?"; 
        jdbcTemplate.update(sql, newAddress, name); 
    }

    public void deleteChain(String name){ 
        String sql = "DELETE hotel_chain WHERE name = ?"; 
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
        String sql = "INSERT INTO chain_emails (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, chainName, email); 
    }

    public void deleteChainEmail(String chainName, String email){ 
        String sql = "DELETE FROM chain_emails WHERE name = ? AND email = ?"; 
        jdbcTemplate.update(sql, chainName, email); 
    }

}
