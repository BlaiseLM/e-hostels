package com.ehotels.app.controller;
import com.ehotels.app.dao.*;
import com.ehotels.app.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelDAO hotelDAO;
    private final HotelChainDAO hotelChainDAO;
    private final RoomDAO roomDAO;

    public HotelController(HotelDAO hotelDAO, HotelChainDAO hotelChainDAO, RoomDAO roomDAO) {
        this.hotelDAO = hotelDAO;
        this.hotelChainDAO = hotelChainDAO;
        this.roomDAO = roomDAO;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        hotelDAO.insertHotel(
            (String) body.get("address"),
            (String) body.get("chainName"),
            (Integer) body.get("starRating"),
            (Integer) body.get("ssn"),
            (String) body.get("employeeAddress"),
            (String) body.get("firstName"),
            (String) body.get("lastName"),
            LocalDate.parse((String) body.get("registrationDate"))
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

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchHotels(
            @RequestParam(required = false) String chainName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer starRating,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(hotelDAO.searchHotels(chainName, address, starRating, phoneNumber, email));
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

    @PostMapping("/room")
    public ResponseEntity<?> insertRoom(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam double priceInDollars, @RequestParam int capacity, @RequestParam String viewOption, @RequestParam boolean extendable) {
        roomDAO.insertRoom(number, hotelAddress, chainName, priceInDollars, capacity, viewOption, extendable);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room")
    public ResponseEntity<?> deleteRoom(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName) {
        roomDAO.deleteRoom(number, hotelAddress, chainName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/room/price")
    public ResponseEntity<?> updateRoomPrice(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam double priceInDollars) {
        roomDAO.updateRoomPrice(number, hotelAddress, chainName, priceInDollars);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/room/capacity")
    public ResponseEntity<?> updateRoomCapacity(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam int capacity) {
        roomDAO.updateRoomCapacity(number, hotelAddress, chainName, capacity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/room/view")
    public ResponseEntity<?> updateRoomView(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam String viewOption) {
        roomDAO.updateRoomView(number, hotelAddress, chainName, viewOption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/room/extendability")
    public ResponseEntity<?> updateRoomExtendability(@RequestParam int number, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam boolean extendable) {
        roomDAO.updateRoomExtendability(number, hotelAddress, chainName, extendable);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/room/amenity")
    public ResponseEntity<?> insertAmenity(@RequestParam int roomNumber, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam String amenity) {
        roomDAO.insertAmenity(roomNumber, hotelAddress, chainName, amenity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/amenity")
    public ResponseEntity<?> deleteAmenity(@RequestParam int roomNumber, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam String amenity) {
        roomDAO.deleteAmenity(roomNumber, hotelAddress, chainName, amenity);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/room/damage")
    public ResponseEntity<?> insertDamage(@RequestParam int roomNumber, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam String damage) {
        roomDAO.insertDamage(roomNumber, hotelAddress, chainName, damage);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/damage")
    public ResponseEntity<?> deleteDamage(@RequestParam int roomNumber, @RequestParam String hotelAddress, @RequestParam String chainName, @RequestParam String damage) {
        roomDAO.deleteDamage(roomNumber, hotelAddress, chainName, damage);
        return ResponseEntity.noContent().build();
    }
}
