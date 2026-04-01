package com.ehotels.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertRoom(int number, String hotelAddress, String chainName) {
        String sql = "INSERT INTO room (number, hotel_address, chain_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, number, hotelAddress, chainName);
    }

    public void deleteRoom(int number, String hotelAddress, String chainName) {
        String sql = "DELETE FROM room WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, number, hotelAddress, chainName);
    }

    public void updateRoomPrice(int number, String hotelAddress, String chainName, double priceInDollars) { // convert to cents internally
        long priceInCents = Math.round(priceInDollars * 100);
        String sql = "UPDATE room SET price = ? WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, priceInCents, number, hotelAddress, chainName);
    }

    public void updateRoomCapacity(int number, String hotelAddress, String chainName, int capacity) {
        String sql = "UPDATE room SET capacity = ? WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, capacity, number, hotelAddress, chainName);
    }

    public void updateRoomView(int number, String hotelAddress, String chainName, String viewOption) {
        String sql = "UPDATE room SET view_option = ? WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, viewOption, number, hotelAddress, chainName);
    }

    public void updateRoomExtendability(int number, String hotelAddress, String chainName, boolean extendable) {
        String sql = "UPDATE room SET extendability = ? WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, extendable, number, hotelAddress, chainName);
    }

    public void insertAmenity(int roomNumber, String hotelAddress, String chainName, String amenity) {
        String sql = "INSERT INTO amenities (room_number, hotel_address, chain_name, amenity) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, roomNumber, hotelAddress, chainName, amenity);
    }

    public void deleteAmenity(int roomNumber, String hotelAddress, String chainName, String amenity) {
        String sql = "DELETE FROM amenities WHERE room_number = ? AND hotel_address = ? AND chain_name = ? AND amenity = ?";
        jdbcTemplate.update(sql, roomNumber, hotelAddress, chainName, amenity);
    }

    public void insertDamage(int roomNumber, String hotelAddress, String chainName, String damage){ 
        String sql = "INSERT INTO damages (room_number, hotel_address, chain_name, damage) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, roomNumber, hotelAddress, chainName, damage);
    }

    public void deleteDamage(int roomNumber, String hotelAddress, String chainName, String damage) {
        String sql = "DELETE FROM damages WHERE room_number = ? AND hotel_address = ? AND chain_name = ? AND damage = ?";
        jdbcTemplate.update(sql, roomNumber, hotelAddress, chainName, damage);
    }

}
