package com.example.service;

import com.example.model.Flight;
import com.example.model.Passenger;
import com.example.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Reservation> reservations;

    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    public void bookReservation(Passenger passenger, Flight flight) {
        Reservation newReservation = new Reservation(passenger, flight);
        reservations.add(newReservation);
        flight.bookSeat();
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.getFlight().cancelSeat();
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

