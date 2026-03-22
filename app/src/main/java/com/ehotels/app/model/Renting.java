package com.ehotels.app.model;
import java.time.LocalDate;

public record Renting(
    int id, 
    LocalDate startDate, 
    LocalDate endDate, 
    LocalDate registrationDate, 
    String payment
){}