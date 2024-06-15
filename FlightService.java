package com.example.service;

import com.example.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private List<Flight> flights;

    public FlightService() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(String flightNumber, String origin, String destination, String departureTime, int availableSeats) {
        Flight newFlight = new Flight(flightNumber, origin, destination, departureTime, availableSeats);
        flights.add(newFlight);
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}

