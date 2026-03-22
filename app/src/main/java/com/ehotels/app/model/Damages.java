package com.ehotels.app.model;

public record Damages(
    int roomNumber, 
    String hotelAddress, 
    String chainNumber, 
    String damage
){}