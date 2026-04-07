package com.ehotels.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertRoom(int number, String hotelAddress, String chainName, double priceInDollars, int capacity, String viewOption, boolean extendable) {
        String sql = "INSERT INTO room (number, hotel_address, chain_name, price, capacity, view_option, extendability) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int priceInCents = (int) Math.round(priceInDollars * 100);
        jdbcTemplate.update(sql, number, hotelAddress, chainName, priceInCents, capacity, viewOption, extendable);
    }

    public void deleteRoom(int number, String hotelAddress, String chainName) {
        String sql = "DELETE FROM room WHERE number = ? AND hotel_address = ? AND chain_name = ?";
        jdbcTemplate.update(sql, number, hotelAddress, chainName);
    }

    public List<Map<String, Object>> searchRoom(Integer number, String hotelAddress, String chainName, Integer price, String priceOp, Integer capacity, String capacityOp, String viewOption, Boolean extendable, LocalDate startDate, LocalDate endDate) {
        List<Object> params = new ArrayList<>();
        String sql = "SELECT r.* FROM room r WHERE 1=1";
        if (number != null){ 
            sql += " AND r.number = ?";        
            params.add(number); 
        }
        if (hotelAddress != null){
            sql += " AND r.hotel_address = ?";
            params.add(hotelAddress); 
        }
        if (chainName != null){
            sql += " AND r.chain_name = ?";     
            params.add(chainName); 
        }
        if (viewOption != null){
            sql += " AND r.view_option = ?";    
            params.add(viewOption); 
        }
        if (extendable != null){
            sql += " AND r.extendability = ?";  
            params.add(extendable); 
        }
        if (price != null && priceOp != null) {
            int priceInCents = price * 100;
            if (priceOp.equals("lt")) {
                sql += " AND r.price < ?";
            } else if (priceOp.equals("gt")) {
                sql += " AND r.price > ?";
            } else {
                sql += " AND r.price = ?";
            }
            params.add(priceInCents);
        }
        if (capacity != null && capacityOp != null) {
            if (capacityOp.equals("lt")) {
                sql += " AND r.capacity < ?";
            } else if (capacityOp.equals("gt")) {
                sql += " AND r.capacity > ?";
            } else {
                sql += " AND r.capacity = ?";
            }
            params.add(capacity);
        }
        if (startDate != null && endDate != null) {
            sql += " AND NOT EXISTS (" +
                "SELECT * FROM booked_rooms b WHERE b.room_number = r.number " +
                "AND b.hotel_address = r.hotel_address AND b.chain_name = r.chain_name " +
                "AND b.period && daterange(TO_DATE(?, 'DD/MM/YYYY'), TO_DATE(?, 'DD/MM/YYYY'), '[)'))" +
                " AND NOT EXISTS (" +
                "SELECT * FROM rented_rooms rr WHERE rr.room_number = r.number " +
                "AND rr.hotel_address = r.hotel_address AND rr.chain_name = r.chain_name " +
                "AND rr.period && daterange(TO_DATE(?, 'DD/MM/YYYY'), TO_DATE(?, 'DD/MM/YYYY'), '[)'))";
            String formattedStart = startDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String formattedEnd = endDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            params.add(formattedStart);
            params.add(formattedEnd);
            params.add(formattedStart);
            params.add(formattedEnd);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
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
