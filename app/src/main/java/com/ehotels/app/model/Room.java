package com.ehotels.app.model;

public record Room(
    int number, 
    String hotelAddress, 
    String chainName, 
    int price, 
    int capacity, 
    String viewOption, 
    boolean extendability
){}