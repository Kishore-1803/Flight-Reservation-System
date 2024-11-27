package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    static List<User> users = User.loadUsersFromFile("user.txt");
    static List<Flight> flights = Flight.loadFlightsFromFile("flights.txt");
    static List<Booking> bookings = loadBookingsFromFile("bookings.txt", users, flights);

    // in terminal
    public BookingManager(List<Booking> bookings) {
        BookingManager.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        Booking.writeBookingToFile(booking, "bookings.txt");
    }


    public List<Booking> viewUserBookings(String username) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUser().getUsername().equals(username)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void addUser(User user) {
        users.add(user);
        User.writeUserToFile(user, "user.txt");
    }

    public List<Booking> getBookings() {
        return bookings;
    }


    // (In Terminal)
    public void viewBookingDetails(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("User: " + booking.getUser().getUsername()); 
                System.out.println("Flight: " + booking.getFlight().getFlightNumber()); 
                System.out.println("Number of Passengers: " + booking.getNumberOfPassengers());
                return;
            }
        }
        System.out.println("Booking with ID " + bookingId + " not found.");
    }

    // (In Terminal)
    public static void viewBookingDetailsusername(String username) {
        boolean foundBooking = false;
        for (Booking booking : bookings) {
            if (booking.getUser().getUsername().equals(username)) {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("User: " + booking.getUser().getUsername());
                System.out.println("Flight: " + booking.getFlight().getFlightNumber());
                System.out.println("Number of Passengers: " + booking.getNumberOfPassengers());
                foundBooking = true;
            }
        }
        if (!foundBooking) {
            System.out.println("No bookings found for user: " + username);
        }
    }

    // (In Terminal)
    public void cancelBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookings.remove(booking);
                System.out.println("Booking with ID " + bookingId + " has been canceled.");
                return;
            }
        }
        System.out.println("Booking with ID " + bookingId + " not found.");
    }

    public boolean cancelbooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookings.remove(booking);
                return true;
            }
        }
        return false;
    }

    public static List<Booking> loadBookingsFromFile(String filename, List<User> users, List<Flight> flights) {
        List<Booking> bookings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); 
                if (parts.length == 4) { 
                    String bookingId = parts[0];
                    String flightNumber = parts[1];
                    String username = parts[2];
                    int numberOfPassengers = Integer.parseInt(parts[3]);
                    User user = User.findUserByUsername(users, username);
                    Flight flight = Flight.findFlightByFlightNumber(flights, flightNumber);
                    if (user != null && flight != null) { 
                        Booking booking = new Booking(bookingId, flight, user, numberOfPassengers);
                        bookings.add(booking);
                    } else {
                        System.out.println("User or flight not found for booking: " + bookingId);
                    }
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return bookings;
    }
}