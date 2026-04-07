package com.ehotels.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RentingDAO {

    private final JdbcTemplate jdbcTemplate;

    public RentingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertRenting(Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate, Integer payment, Integer empSsn, String empChainName, String empHotelAddress) {
    String sql = "INSERT INTO renting (room_number, hotel_address, chain_name, customer_id, start_date, end_date, registration_date, payment) VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE, ?) RETURNING id";
    Integer rentId = jdbcTemplate.queryForObject(sql, Integer.class, roomNumber, hotelAddress, chainName, customerId, startDate, endDate, payment);
    String processedBySql = "INSERT INTO processedby (e_ssn, chain_name, hotel_address, rent_id, cust_id) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(processedBySql, empSsn, empChainName, empHotelAddress, rentId, customerId);
}

    public void deleteRenting(Integer id){ 
        String sql = "DELETE FROM renting WHERE id = ?"; 
        jdbcTemplate.update(sql, id); 
    }

    public void insertBooking(Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate){ 
        String sql = "INSERT INTO booking (room_number, hotel_address, chain_name, customer_id, start_date, end_date, registration_date) VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE)"; 
        jdbcTemplate.update(sql, roomNumber, hotelAddress, chainName, customerId, startDate, endDate); 
    }

    public void deleteBooking(Integer id){
        String sql = "DELETE FROM booking WHERE id = ?"; 
        jdbcTemplate.update(sql, id);  
    }

    public void updateRenting(Integer id, Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate, LocalDate registrationDate, Integer payment){ 
        List<String> assignments = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (roomNumber != null) {
            assignments.add("room_number = ?");
            params.add(roomNumber);
        }
        if (hotelAddress != null) {
            assignments.add("hotel_address = ?");
            params.add(hotelAddress);
        }
        if (chainName != null) {
            assignments.add("chain_name = ?");
            params.add(chainName);
        }
        if (customerId != null) {
            assignments.add("customer_id = ?");
            params.add(customerId);
        }
        if (startDate != null) {
            assignments.add("start_date = ?");
            params.add(startDate);
        }
        if (endDate != null) {
            assignments.add("end_date = ?");
            params.add(endDate);
        }
        if (registrationDate != null) {
            assignments.add("registration_date = ?");
            params.add(registrationDate);
        }
        if (payment != null) {
            assignments.add("payment = ?");
            params.add(payment);
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update for renting id=" + id);
        }
        String sql = "UPDATE renting SET " + String.join(", ", assignments) + " WHERE id = ?";
        params.add(id);
        jdbcTemplate.update(sql, params.toArray());
    }
        
    public void updateBooking(Integer id, Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate, LocalDate registrationDate){ 
        List<String> assignments = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (roomNumber != null) {
            assignments.add("room_number = ?");
            params.add(roomNumber);
        }
        if (hotelAddress != null) {
            assignments.add("hotel_address = ?");
            params.add(hotelAddress);
        }
        if (chainName != null) {
            assignments.add("chain_name = ?");
            params.add(chainName);
        }
        if (customerId != null) {
            assignments.add("customer_id = ?");
            params.add(customerId);
        }
        if (startDate != null) {
            assignments.add("start_date = ?");
            params.add(startDate);
        }
        if (endDate != null) {
            assignments.add("end_date = ?");
            params.add(endDate);
        }
        if (registrationDate != null) {
            assignments.add("registration_date = ?");
            params.add(registrationDate);
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update for booking id=" + id);
        }
        String sql = "UPDATE booking SET " + String.join(", ", assignments) + " WHERE id = ?";
        params.add(id);
        jdbcTemplate.update(sql, params.toArray());   
    }

    public List<Map<String, Object>> searchRenting(Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate, LocalDate registrationDate){
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (roomNumber != null) {
            filters.add("room_number = ?");
            params.add(roomNumber);
        }
        if (hotelAddress != null) {
            filters.add("hotel_address = ?");
            params.add(hotelAddress);
        }
        if (chainName != null) {
            filters.add("chain_name = ?");
            params.add(chainName);
        }
        if (customerId != null) {
            filters.add("customer_id = ?");
            params.add(customerId);
        }
        if (startDate != null) {
            filters.add("start_date = ?");
            params.add(startDate);
        }
        if (endDate != null) {
            filters.add("end_date = ?");
            params.add(endDate);
        }
        if (registrationDate != null) {
            filters.add("registration_date = ?");
            params.add(registrationDate);
        }
        String sql = "SELECT * FROM renting";
        if (!filters.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }

    public List<Map<String, Object>> searchBooking(Integer roomNumber, String hotelAddress, String chainName, Integer customerId, LocalDate startDate, LocalDate endDate, LocalDate registrationDate){
        List<String> filters = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        if (roomNumber != null) {
            filters.add("room_number = ?");
            params.add(roomNumber);
        }
        if (hotelAddress != null) {
            filters.add("hotel_address = ?");
            params.add(hotelAddress);
        }
        if (chainName != null) {
            filters.add("chain_name = ?");
            params.add(chainName);
        }
        if (customerId != null) {
            filters.add("customer_id = ?");
            params.add(customerId);
        }
        if (startDate != null) {
            filters.add("start_date = ?");
            params.add(startDate);
        }
        if (endDate != null) {
            filters.add("end_date = ?");
            params.add(endDate);
        }
        if (registrationDate != null) {
            filters.add("registration_date = ?");
            params.add(registrationDate);
        }
        String sql = "SELECT * FROM booking";
        if (!filters.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", filters);
        }
        return jdbcTemplate.queryForList(sql, params.toArray());
    }

    @Transactional
    public void convertBookingToRenting(Integer ssn, String chainName, String hotelAddress, Integer bookingId, Integer payment) {
        String sql = "INSERT INTO convertTo (ssn, chain_name, hotel_address, booking_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ssn, chainName, hotelAddress, bookingId);
    }
}