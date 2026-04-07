package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ViewDAO {

    private final JdbcTemplate jdbcTemplate;

    public ViewDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getFullChainView() {
        return jdbcTemplate.queryForList("SELECT * FROM full_chain_view");
    }

    public List<Map<String, Object>> getFullHotelView() {
        return jdbcTemplate.queryForList("SELECT * FROM full_hotel_view");
    }
}