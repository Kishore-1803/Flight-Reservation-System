import Functions.*;
import javax.swing.*;
import java.awt.*;

public class ExistingUserGUI {
    private JFrame frame;
    private BookingManager bookingManager;

    public ExistingUserGUI(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Existing User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200); 
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Enter Username:");
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 20));
        JLabel passwordLabel = new JLabel("Enter Password:");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 20));
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = User.findUserByUsername(bookingManager.getUsers(), username);
            if (user != null && authenticate(user, password)) {
                new UserDashboardGUI(bookingManager, user);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private boolean authenticate(User user, String password) {
        return user.getPassword().equals(password);
    }
}