package com.ehotels.app.model;
import java.time.LocalDate;

public record Employee(
    int ssn, 
    String chainName, 
    String hotelAddress, 
    String firstName, 
    String lastName, 
    String address, 
    LocalDate registrationDate
){}