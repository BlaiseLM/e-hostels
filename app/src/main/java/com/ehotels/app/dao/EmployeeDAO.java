package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // waiting for query
    public void insertEmployee(/* employee fields */){ 

    }

    // waiting for query
    public void deleteEmployee(/* PK */){ 

    }

    // waiting for query
    public void updateEmployee(/* fields */){ 

    }

    // waiting for query
    public void insertRole(/* employeeId, role */){ 

    }

    // waiting for query
    public void deleteRole(/* employeeId, role */){ 
        
    }

}
