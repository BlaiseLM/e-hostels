package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class HotelDAO {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final JdbcTemplate jdbcTemplate;

    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertHotel(String address, String chainName, int starRating) {
        String sql = "INSERT INTO hotel (address, chain_name, star_rating) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, address, chainName, starRating);
    }

    public void updateHotelRating(String chainName, String address, int newRating) {
        String sql = "UPDATE hotel SET star_rating = ? WHERE chain_name = ? AND address = ?";
        jdbcTemplate.update(sql, newRating, chainName, address);
    }

    public void deleteHotel(String chainName, String address) {
        String sql = "DELETE FROM hotel WHERE chain_name = ? AND address = ?";
        jdbcTemplate.update(sql, chainName, address);
    }

    public void insertHotelPhone(String chainName, String address, String phoneNumber) {
        String sql = "INSERT INTO hotel_phone_numbers (chain_name, address, phone_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chainName, address, phoneNumber);
    }

    public void deleteHotelPhone(String chainName, String address, String phoneNumber) {
        String sql = "DELETE FROM hotel_phone_numbers WHERE chain_name = ? AND address = ? AND phone_number = ?";
        jdbcTemplate.update(sql, chainName, address, phoneNumber);
    }

    public void insertHotelEmail(String chainName, String address, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        String sql = "INSERT INTO hotel_emails (chain_name, address, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chainName, address, email);
    }

    public void deleteHotelEmail(String chainName, String address, String email) {
        String sql = "DELETE FROM hotel_emails WHERE chain_name = ? AND address = ? AND email = ?";
        jdbcTemplate.update(sql, chainName, address, email);
    }

    public List<Map<String, Object>> searchHotels(String chainName, String address, Integer starRating, String phoneNumber, String email) {
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (chainName != null) {
            filters.add("hot.chain_name = ?");
            params.add(chainName);
        }
        if (address != null) {
            filters.add("hot.address = ?");
            params.add(address);
        }
        if (starRating != null) {
            filters.add("hot.star_rating = ?");
            params.add(starRating);
        }
        if (phoneNumber != null) {
            filters.add("hp.phone_number = ?");
            params.add(phoneNumber);
        }
        if (email != null) {
            filters.add("he.email = ?");
            params.add(email);
        }
        String sql = "SELECT hot.*, hp.phone_number, he.email FROM hotel hot " +
                     "JOIN hotel_phone_numbers hp ON hot.chain_name = hp.chain_name AND hot.address = hp.address " +
                     "JOIN hotel_emails he ON hot.chain_name = he.chain_name AND hot.address = he.address";
        if (!filters.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

}
