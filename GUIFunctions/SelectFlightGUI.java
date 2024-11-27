import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class SelectFlightGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private User user;
    private List<Flight> availableFlights;
    private int numberOfPassengers;

    public SelectFlightGUI(BookingManager bookingManager, User user, List<Flight> availableFlights, int numberOfPassengers) {
        this.bookingManager = bookingManager;
        this.user = user;
        this.availableFlights = availableFlights;
        this.numberOfPassengers = numberOfPassengers;
        initialize();
    }


    private void initialize() {
        frame = new JFrame("Select Flight");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel flightsPanel = new JPanel();
        flightsPanel.setLayout(new GridLayout(availableFlights.size(), 1, 10, 10));
        ButtonGroup flightGroup = new ButtonGroup();

        for (Flight flight : availableFlights) {
            JRadioButton flightButton = new JRadioButton(flight.toString());
            flightButton.setActionCommand(flight.toString());
            flightGroup.add(flightButton);
            flightsPanel.add(flightButton);
        }

        JButton bookButton = new JButton("Book Flight");
        bookButton.addActionListener(e -> {
            ButtonModel selectedModel = flightGroup.getSelection();
            if (selectedModel != null) {
                String selectedFlightDetails = selectedModel.getActionCommand();
                for (Flight flight : availableFlights) {
                    if (flight.toString().equals(selectedFlightDetails)) {
                        Booking newBooking = new Booking(Booking.generateBookingId(), flight, user, numberOfPassengers);
                        bookingManager.addBooking(newBooking);
                        JOptionPane.showMessageDialog(frame, "Flight booked successfully!");
                        frame.dispose();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a flight.");
            }
        });

        frame.add(new JScrollPane(flightsPanel), BorderLayout.CENTER);
        frame.add(bookButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
