import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class CancelBookingGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private User user;

    public CancelBookingGUI(BookingManager bookingManager, User user) {
        this.bookingManager = bookingManager;
        this.user = user;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cancel Booking");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Booking> userBookings = bookingManager.viewUserBookings(user.getUsername());
        if (userBookings.isEmpty()) {
            panel.add(new JLabel("You have no bookings to cancel."));
        } else {
            ButtonGroup buttonGroup = new ButtonGroup();
            for (Booking booking : userBookings) {
                JRadioButton radioButton = new JRadioButton(booking.toString());
                radioButton.setActionCommand(booking.getBookingId());
                buttonGroup.add(radioButton);
                panel.add(radioButton);
            }
            JButton cancelButton = new JButton("Cancel Selected Booking");
            cancelButton.addActionListener(e -> {
                String selectedBookingId = buttonGroup.getSelection().getActionCommand();
                boolean success = bookingManager.cancelbooking(selectedBookingId);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Booking canceled successfully.");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to cancel booking. Please try again.");
                }
            });
            panel.add(cancelButton);
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
