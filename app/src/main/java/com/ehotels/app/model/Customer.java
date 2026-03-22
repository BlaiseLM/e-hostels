package com.ehotels.app.model;
import java.time.LocalDate;

public record Customer(
    int id, 
    String firstName,
    String lastName, 
    String fullName, 
    String address, 
    String idType, 
    String idValue, 
    LocalDate registrationDate
){}