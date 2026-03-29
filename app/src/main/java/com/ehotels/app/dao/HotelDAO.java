package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDAO {

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
        String sql = "INSERT INTO hotel_emails (chain_name, address, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chainName, address, email);
    }

    public void deleteHotelEmail(String chainName, String address, String email) {
        String sql = "DELETE FROM hotel_emails WHERE chain_name = ? AND address = ? AND email = ?";
        jdbcTemplate.update(sql, chainName, address, email);
    }

}
