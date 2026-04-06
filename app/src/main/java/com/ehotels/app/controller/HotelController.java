package com.ehotels.app.controller;
import com.ehotels.app.dao.*;
import com.ehotels.app.model.*;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelDAO hotelDAO;
    private final HotelChainDAO hotelChainDAO;

    public HotelController(HotelDAO hotelDAO, HotelChainDAO hotelChainDAO) { 
        this.hotelDAO = hotelDAO;
        this.hotelChainDAO = hotelChainDAO;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Hotel hotel) {
        hotelDAO.insertHotel(
                hotel.address(),
                hotel.chainName(),
                hotel.starRating()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete( @RequestParam String chainName, @RequestParam String address) {
        hotelDAO.deleteHotel(chainName, address);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rating")
    public ResponseEntity<?> updateHotelRating(@RequestParam String chainName, @RequestParam String address, @RequestParam int newRating) {
        hotelDAO.updateHotelRating(chainName, address, newRating);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/phone")
    public ResponseEntity<?> insertHotelPhone(@RequestParam String chainName, @RequestParam String address, @RequestParam String phoneNumber) {
        hotelDAO.insertHotelPhone(chainName, address, phoneNumber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/phone")
    public ResponseEntity<?> deleteHotelPhone(@RequestParam String chainName, @RequestParam String address, @RequestParam String phoneNumber) {
        hotelDAO.deleteHotelPhone(chainName, address, phoneNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/email")
    public ResponseEntity<?> insertHotelEmail(@RequestParam String chainName, @RequestParam String address, @RequestParam String email) {
        hotelDAO.insertHotelEmail(chainName, address, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/email")
    public ResponseEntity<?> deleteHotelEmail(@RequestParam String chainName, @RequestParam String address, @RequestParam String email) {
        hotelDAO.deleteHotelEmail(chainName, address, email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/chain")
    public ResponseEntity<?> createChain(@RequestParam String name, @RequestParam String centralOfficeAddress) {
        hotelChainDAO.insertChain(name, centralOfficeAddress);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/chain/address")
    public ResponseEntity<?> updateChainAddress(@RequestParam String name, @RequestParam String newAddress) {
        hotelChainDAO.updateChainAddress(name, newAddress);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/chain")
    public ResponseEntity<?> deleteChain(@RequestParam String name) {
        hotelChainDAO.deleteChain(name);
        return ResponseEntity.noContent().build();
    }

   @PostMapping("/chain/phone")
    public ResponseEntity<?> insertChainPhone(@RequestParam String chainName, @RequestParam String phoneNumber) {
        hotelChainDAO.insertChainPhone(chainName, phoneNumber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/chain/phone")
    public ResponseEntity<?> deleteChainPhone(@RequestParam String chainName, @RequestParam String phoneNumber) {
        hotelChainDAO.deleteChainPhone(chainName, phoneNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/chain/email")
    public ResponseEntity<?> insertChainEmail(@RequestParam String chainName, @RequestParam String email) {
        hotelChainDAO.insertChainEmail(chainName, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/chain/email")
    public ResponseEntity<?> deleteChainEmail(@RequestParam String chainName, @RequestParam String email) {
        hotelChainDAO.deleteChainEmail(chainName, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/chain/search")
    public ResponseEntity<?> searchChain(
        @RequestParam(required = false) String chainName, 
        @RequestParam(required = false) String phoneNumber, 
        @RequestParam(required = false) String email
    ) {
        List<Map<String, Object>> results = hotelChainDAO.searchHotelChain(chainName, phoneNumber, email);
        return ResponseEntity.ok(results);
    }
}