package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RentingDAO {

    private final JdbcTemplate jdbcTemplate;

    public RentingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // waiting for query
    public void insertRenting(/* renting fields */){ 

    }

    // waiting for query
    public void deleteRenting(/* PK */){ 

    }

    // waiting for query
    public void updateRenting(/* fields */){ 
        
    }
}
