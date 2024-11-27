package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flight {
    private String flightNumber;
    private String departureCity;
    private String destination;
    private String travelDate;
    private boolean available;

    public Flight(String flightNumber, String departureCity, String destination, String travelDate, boolean available) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destination = destination;
        this.travelDate = travelDate;
        this.available = available;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static List<Flight> searchFlights(String departureCity, String destination, String travelDate) {
        List<Flight> flights = loadFlightsFromFile("flights.txt");
        List<Flight> availableFlights = new ArrayList<>(); 
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity) &&
                flight.getDestination().equalsIgnoreCase(destination) &&
                flight.getTravelDate().equalsIgnoreCase(travelDate) &&
                flight.isAvailable()) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }

    public static List<Flight> loadFlightsFromFile(String filename) {
        List<Flight> flights = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); 
                if (parts.length == 5) { 
                    String flightNumber = parts[0];
                    String departureCity = parts[1];
                    String destination = parts[2];
                    String travelDate = parts[3];
                    boolean available = Boolean.parseBoolean(parts[4]);
                    Flight flight = new Flight(flightNumber, departureCity, destination, travelDate, available);
                    flights.add(flight);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return flights;
    }

    public static Flight findFlightByFlightNumber(List<Flight> flights, String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null; 
    }

    @Override
    public String toString() {
        return "flightNumber='" + flightNumber + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", destination='" + destination + '\'' +
                ", travelDate='" + travelDate + '\'' +
                ", available=" + available + '\'';
    }
}        