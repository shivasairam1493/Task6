package com.example.model;

public class Reservation {
    private Passenger passenger;
    private Flight flight;

    public Reservation(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }
}

