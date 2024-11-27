import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class ViewBookingsGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private User user;

    public ViewBookingsGUI(BookingManager bookingManager, User user) {
        this.bookingManager = bookingManager;
        this.user = user;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        List<Booking> userBookings = bookingManager.viewUserBookings(user.getUsername());
        if (userBookings.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No bookings found.", "My Bookings", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder bookingInfo = new StringBuilder("Your bookings:\n");
            for (Booking booking : userBookings) {
                bookingInfo.append(booking.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, bookingInfo.toString(), "My Bookings", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
