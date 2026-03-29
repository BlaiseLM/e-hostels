package com.ehotels.app.dao; 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO { 

    private final JdbcTemplate jdbcTemplate; 

    public CustomerDAO(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    }

    // waiting for query
    public void insertCustomer(/* customer fields */){ 

    }

    // waiting for query
    public void deleteCustomer(/* PK */){ 

    }

    // waiting for query
    public void updateCustomer(/* fields */){ 
        
    }

}