package com.ehotels.app.controller;
import com.ehotels.app.dao.*;
import com.ehotels.app.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentings")
public class RentingController {
    private final RentingDAO rentingDAO;

    public RentingController(RentingDAO rentingDAO) {
        this.rentingDAO = rentingDAO;
    }

    @PostMapping("/booking")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        rentingDAO.insertBooking(
            booking.roomNumber(),
            booking.hotelAddress(),
            booking.chainName(),
            booking.customerId(),
            booking.startDate(),
            booking.endDate()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/booking")
    public ResponseEntity<?> deleteBooking(@RequestParam Integer id) {
        rentingDAO.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/booking")
    public ResponseEntity<?> updateBooking(@RequestParam Integer id, @RequestBody Booking booking) {
        rentingDAO.updateBooking(
            id,
            booking.roomNumber(),
            booking.hotelAddress(),
            booking.chainName(),
            booking.customerId(),
            booking.startDate(),
            booking.endDate(),
            booking.registrationDate()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/booking/search")
    public ResponseEntity<List<Map<String, Object>>> searchBooking(
        @RequestParam(required = false) Integer roomNumber,
        @RequestParam(required = false) String hotelAddress,
        @RequestParam(required = false) String chainName,
        @RequestParam(required = false) Integer customerId,
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate,
        @RequestParam(required = false) LocalDate registrationDate
    ) {
        return ResponseEntity.ok(rentingDAO.searchBooking(roomNumber, hotelAddress, chainName, customerId, startDate, endDate, registrationDate));
    }

    @PostMapping()
    public ResponseEntity<?> createRenting(
        @RequestBody Renting renting,
        @RequestParam Integer empSsn
    ) {
        rentingDAO.insertRenting(
            renting.roomNumber(),
            renting.hotelAddress(),
            renting.chainName(),
            renting.customerId(),
            renting.startDate(),
            renting.endDate(),
            renting.payment(),
            empSsn
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRenting(@RequestParam Integer id) {
        rentingDAO.deleteRenting(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateRenting(@RequestParam Integer id, @RequestBody Renting renting) {
        rentingDAO.updateRenting(
            id,
            renting.roomNumber(),
            renting.hotelAddress(),
            renting.chainName(),
            renting.customerId(),
            renting.startDate(),
            renting.endDate(),
            renting.registrationDate(),
            renting.payment()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchRenting(
        @RequestParam(required = false) Integer roomNumber,
        @RequestParam(required = false) String hotelAddress,
        @RequestParam(required = false) String chainName,
        @RequestParam(required = false) Integer customerId,
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate,
        @RequestParam(required = false) LocalDate registrationDate
    ) {
        return ResponseEntity.ok(rentingDAO.searchRenting(roomNumber, hotelAddress, chainName, customerId, startDate, endDate, registrationDate));
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertBookingToRenting(
        @RequestParam Integer ssn,
        @RequestParam String chainName,
        @RequestParam String hotelAddress,
        @RequestParam Integer bookingId,
        @RequestParam Integer payment
    ) {
        rentingDAO.convertBookingToRenting(ssn, chainName, hotelAddress, bookingId, payment);
        return ResponseEntity.ok().build();
    }
}