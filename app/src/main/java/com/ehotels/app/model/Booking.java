package com.ehotels.app.model;
import java.time.LocalDate;

public record Booking(
    Integer id,
    Integer roomNumber, 
    String hotelAddress, 
    String chainName, 
    Integer customerId, 
    LocalDate startDate, 
    LocalDate endDate, 
    LocalDate registrationDate
){}