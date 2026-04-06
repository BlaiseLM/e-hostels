package com.ehotels.app.model;
import java.time.LocalDate;

public record Customer(
    Integer id,
    String firstName,
    String lastName, 
    String address, 
    String idType, 
    String idValue, 
    LocalDate registrationDate
){}