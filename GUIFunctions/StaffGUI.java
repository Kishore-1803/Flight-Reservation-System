import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class StaffGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private List<User> staffMembers;
    private String username;

    public StaffGUI(BookingManager bookingManager, List<User> staffMembers, String username) {
        this.bookingManager = bookingManager;
        this.staffMembers = staffMembers;
        this.username = username;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Staff Dashboard");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome Staff, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel);
    
        JButton viewBookingsButton = new JButton("View All Bookings");
        JButton addStaffButton = new JButton("Add Staff Member");
        JButton logoutButton = new JButton("Logout");
    
        viewBookingsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addStaffButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
    
        viewBookingsButton.addActionListener(e -> viewAllBookings());
        addStaffButton.addActionListener(e -> addStaffMember());
        logoutButton.addActionListener(e -> logout());
    
        panel.add(viewBookingsButton);
        panel.add(addStaffButton);
        panel.add(logoutButton);
    
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void viewAllBookings() {
        List<Booking> bookings = bookingManager.getBookings();
        StringBuilder bookingInfo = new StringBuilder();
        for (Booking booking : bookings) {
            bookingInfo.append(booking.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, bookingInfo.toString(), "All Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addStaffMember() {
        new AddStaffMemberGUI(staffMembers);
    }

    private void logout() {
        frame.dispose();
    }
}
