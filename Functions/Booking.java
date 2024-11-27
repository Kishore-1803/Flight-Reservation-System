package Functions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private Flight flight;
    private User user;
    private int numberOfPassengers;

    public Booking(String bookingId, Flight flight, User user, int numberOfPassengers) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.user = user;
        this.numberOfPassengers = numberOfPassengers;
    }

    List<User> users = User.loadUsersFromFile("user.txt");
    List<Flight> flights = Flight.loadFlightsFromFile("flights.txt");


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    //(in terminal)
    public void viewBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Details: " + flight);
        System.out.println("User Details: " + user);
        System.out.println("Number of Passengers: " + numberOfPassengers);
    }

    
    //(in terminal)
    public void modifyBooking(Flight newFlight, int newNumberOfPassengers) {
        this.flight = newFlight;
        this.numberOfPassengers = newNumberOfPassengers;
    }

    public static String generateBookingId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    //(in terminal)
    public static void booking(BookingManager bookingManager, Scanner scanner, User currentUser) {
        System.out.println("Search for available flights:");
        System.out.print("Enter departure city: ");
        String departureCity = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter travel date (YYYY-MM-DD): ");
        String travelDate = scanner.nextLine();
        List<Flight> availableFlights = Flight.searchFlights(departureCity, destination, travelDate);
    
        if (availableFlights.isEmpty()) {
            System.out.println("No available flights found for the selected criteria.");
        } else {
            System.out.println("Available Flights:");
            for (int i = 0; i < availableFlights.size(); i++) {
                System.out.println((i + 1) + ". " + availableFlights.get(i));
            }
            System.out.print("Enter the number corresponding to the flight you want to book: ");
            int flightChoice = scanner.nextInt();
            scanner.nextLine(); 
    
            if (flightChoice >= 1 && flightChoice <= availableFlights.size()) {
                Flight selectedFlight = availableFlights.get(flightChoice - 1);
                System.out.print("Enter the number of passengers: ");
                int numberOfPassengers = scanner.nextInt();
                scanner.nextLine(); 
                Booking newBooking = new Booking(generateBookingId(), selectedFlight, currentUser, numberOfPassengers);
                bookingManager.addBooking(newBooking);
                writeBookingToFile(newBooking, "bookings.txt");
                System.out.println("Booking successful. Your booking ID is: " + newBooking.getBookingId());
            } else {
                System.out.println("Invalid flight choice.");
            }
        }
    }   
    
    public static void writeBookingToFile(Booking booking, String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(booking.getBookingId() + "," + booking.getFlight().getFlightNumber() + "," +booking.getUser().getUsername() + "," + booking.getNumberOfPassengers() + "\n");
        }catch (IOException e) {
            System.out.println("Error writing booking data to " + filename + ": " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", flight=" + flight +
                ", user=" + user +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }
}