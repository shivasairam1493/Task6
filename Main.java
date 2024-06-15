package com.example;

import com.example.model.Flight;
import com.example.model.Passenger;
import com.example.service.FlightService;
import com.example.service.ReservationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static FlightService flightService = new FlightService();
    private static ReservationService reservationService = new ReservationService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeFlights();

        while (true) {
            System.out.println("\nAirline Reservation System");
            System.out.println("1. View All Flights");
            System.out.println("2. Book a Reservation");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View All Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllFlights();
                    break;
                case 2:
                    bookReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewAllReservations();
                    break;
                case 5:
                    System.out.println("Exiting Airline Reservation System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void initializeFlights() {
        flightService.addFlight("ABC123", "New York", "Los Angeles", "10:00 AM", 100);
        flightService.addFlight("DEF456", "Los Angeles", "Chicago", "12:00 PM", 80);
        flightService.addFlight("GHI789", "Chicago", "Miami", "2:00 PM", 120);
    }

    private static void viewAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        System.out.println("\nAll Flights:");
        for (Flight flight : flights) {
            System.out.println(flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination() +
                    ", Departure Time: " + flight.getDepartureTime() + ", Available Seats: " + flight.getAvailableSeats());
        }
    }

    private static void bookReservation() {
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();

        System.out.print("Enter passenger age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter passenger contact info: ");
        String contactInfo = scanner.nextLine();

        Passenger passenger = new Passenger(name, age, contactInfo);

        System.out.print("Enter flight number to book reservation: ");
        String flightNumber = scanner.nextLine();

        Flight flight = flightService.findFlightByNumber(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        reservationService.bookReservation(passenger, flight);
        System.out.println("Reservation booked successfully for flight " + flightNumber);
    }

    private static void cancelReservation() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("Select a reservation to cancel:");
        int index = 1;
        for (Reservation reservation : reservations) {
            System.out.println(index + ". " + reservation.getPassenger().getName() + " - " +
                    reservation.getFlight().getFlightNumber() + " (" + reservation.getFlight().getOrigin() + " to " +
                    reservation.getFlight().getDestination() + ")");
            index++;
        }

        System.out.print("Enter your choice (1-" + reservations.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > reservations.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Reservation reservation = reservations.get(choice - 1);
        reservationService.cancelReservation(reservation);
        System.out.println("Reservation canceled successfully.");
    }

    private static void viewAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\nAll Reservations:");
        for (Reservation reservation : reservations) {
            Passenger passenger = reservation.getPassenger();
            Flight flight = reservation.getFlight();
            System.out.println(passenger.getName() + " - " + flight.getFlightNumber() + " (" +
                    flight.getOrigin() + " to " + flight.getDestination() + ")");
        }
    }
}

