import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Functions.*;

public class StaffLoginGUI {
    private JFrame frame;
    private BookingManager bookingManager;
    private List<User> staffMembers; 

    public StaffLoginGUI(BookingManager bookingManager, List<User> staffMembers) {
        this.bookingManager = bookingManager;
        this.staffMembers = staffMembers;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Staff Login");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 20));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 20));
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticate(username, password)) {
                    new StaffGUI(bookingManager, staffMembers, username); 
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again.");
                }
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

    private boolean authenticate(String username, String password) {
        for (User staff : staffMembers) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    
}
