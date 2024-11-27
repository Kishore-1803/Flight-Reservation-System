import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class MainGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private List<User> staffMembers;

    public MainGUI() {
        bookingManager = initializeBookingManager();
        initializeStaffMembers();
        frame = new JFrame("BLUERIDGE AIRLINES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to BLUERIDGE AIRLINES", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        mainPanel.add(welcomeLabel);

        JButton staffButton = new JButton("Staff");
        JButton existingUserButton = new JButton("Existing User");
        JButton newUserButton = new JButton("New User");
        JButton exitButton = new JButton("Exit");

        staffButton.addActionListener(e -> openStaffPanel());
        existingUserButton.addActionListener(e -> openExistingUserPanel());
        newUserButton.addActionListener(e -> openNewUserPanel());
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(staffButton);
        mainPanel.add(existingUserButton);
        mainPanel.add(newUserButton);
        mainPanel.add(exitButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private BookingManager initializeBookingManager() {
        List<User> users = User.loadUsersFromFile("user.txt");
        List<Flight> flights = Flight.loadFlightsFromFile("flights.txt");
        List<Booking> bookings = BookingManager.loadBookingsFromFile("bookings.txt", users, flights);
        return new BookingManager(bookings);
    }

    private void initializeStaffMembers() {
        staffMembers = User.loadStaffFromFile("staff.txt");
    }

    private void openStaffPanel() {
        new StaffLoginGUI(bookingManager, staffMembers);
    }

    private void openExistingUserPanel() {
        new ExistingUserGUI(bookingManager);
    }

    private void openNewUserPanel() {
        new NewUserGUI(bookingManager);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
