import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class MakeBookingGUI {
    private JFrame frame;

    public MakeBookingGUI(BookingManager bookingManager, User user) {
        frame = new JFrame("Booking");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel departureLabel = new JLabel("Departure City:");
        JTextField departureField = new JTextField();
        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationField = new JTextField();
        JLabel travelDateLabel = new JLabel("Travel Date (YYYY-MM-DD):");
        JTextField travelDateField = new JTextField();
        JLabel passengersLabel = new JLabel("Number of Passengers:");
        JTextField passengersField = new JTextField();
        JButton searchButton = new JButton("Search Flights");

        searchButton.addActionListener(e -> {
            String departureCity = departureField.getText();
            String destination = destinationField.getText();
            String travelDate = travelDateField.getText();
            int numberOfPassengers = Integer.parseInt(passengersField.getText());

            List<Flight> availableFlights = Flight.searchFlights(departureCity, destination, travelDate);
            if (availableFlights.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No available flights found for the selected criteria.");
            } else {
                new SelectFlightGUI(bookingManager, user, availableFlights, numberOfPassengers);
                frame.dispose();
            }
        });

        frame.add(departureLabel);
        frame.add(departureField);
        frame.add(destinationLabel);
        frame.add(destinationField);
        frame.add(travelDateLabel);
        frame.add(travelDateField);
        frame.add(passengersLabel);
        frame.add(passengersField);
        frame.add(new JLabel());
        frame.add(searchButton);

        frame.setVisible(true);
    }
}
