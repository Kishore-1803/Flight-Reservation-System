import javax.swing.*;
import java.awt.*;
import Functions.*;

public class UserDashboardGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private User user;

    public UserDashboardGUI(BookingManager bookingManager, User user) {
        this.bookingManager = bookingManager;
        this.user = user;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("User Dashboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel welcomeLabel = new JLabel("Hello, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomePanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1 , 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        JButton viewBookingsButton = new JButton("View My Bookings");
        JButton cancelButton = new JButton("Cancel a Booking");
        JButton makeBookingButton = new JButton("Make a Booking");
        JButton logoutButton = new JButton("Logout");

        viewBookingsButton.addActionListener(e -> new ViewBookingsGUI(bookingManager, user));
        cancelButton.addActionListener(e -> new CancelBookingGUI(bookingManager, user));
        makeBookingButton.addActionListener(e -> new MakeBookingGUI(bookingManager, user));
        logoutButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(viewBookingsButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(makeBookingButton);
        buttonPanel.add(logoutButton);

        frame.add(welcomePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
