package com.ehotels.app.controller;
import com.ehotels.app.dao.*;
import com.ehotels.app.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelDAO hotelDAO;

    // Constructor
    public HotelController(HotelDAO hotelDAO) { this.hotelDAO = hotelDAO;}

    // Create a new hotel
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Hotel hotel) {
        hotelDAO.insertHotel(
                hotel.address(),
                hotel.chainName(),
                hotel.starRating()
        );
        return ResponseEntity.ok().build();
    }

    //delete a hotel
    @DeleteMapping
    public ResponseEntity<?> delete( @RequestParam String chainName, @RequestParam String address) {
        hotelDAO.deleteHotel(chainName, address);
        return ResponseEntity.noContent().build();
    }

    // Update hotel rating
    @PutMapping("/rating")
    public ResponseEntity<?> updateHotelRating(@RequestParam String chainName, @RequestParam String address, @RequestParam int newRating) {
        hotelDAO.updateHotelRating(chainName, address, newRating);
        return ResponseEntity.ok().build();
    }

    //Insert hotel phone number
    @PostMapping("/phone")
    public ResponseEntity<?> insertHotelPhone(@RequestParam String chainName, @RequestParam String address, @RequestParam String phoneNumber) {
        hotelDAO.insertHotelPhone(chainName, address, phoneNumber);
        return ResponseEntity.ok().build();
    }

    //Delete hotel phone number
    @DeleteMapping("/phone")
    public ResponseEntity<?> deleteHotelPhone(@RequestParam String chainName, @RequestParam String address, @RequestParam String phoneNumber) {
        hotelDAO.deleteHotelPhone(chainName, address, phoneNumber);
        return ResponseEntity.noContent().build();
    }

    //insert hotel email
    @PostMapping("/email")
    public ResponseEntity<?> insertHotelEmail(@RequestParam String chainName, @RequestParam String address, @RequestParam String email) {
        hotelDAO.insertHotelEmail(chainName, address, email);
        return ResponseEntity.ok().build();
    }

    //delete hotel email
    @DeleteMapping("/email")
    public ResponseEntity<?> deleteHotelEmail(@RequestParam String chainName, @RequestParam String address, @RequestParam String email) {
        hotelDAO.deleteHotelEmail(chainName, address, email);
        return ResponseEntity.noContent().build();
    }

    //serach hotels
    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchHotels(
            @RequestParam(required = false) String chainName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer starRating,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(hotelDAO.searchHotels(chainName, address, starRating, phoneNumber, email));
    }
}