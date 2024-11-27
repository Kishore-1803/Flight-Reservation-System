import javax.swing.*;
import java.awt.*;
import java.util.List;
import Functions.*;

public class AddStaffMemberGUI {
    private JFrame frame;
    private List<User> staffMembers;

    public AddStaffMemberGUI(List<User> staffMembers) {
        this.staffMembers = staffMembers;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Add New Staff Member");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User newStaff = new User(username, password, "", "");
                staffMembers.add(newStaff);
                User.writeStaffToFile(newStaff, "staff.txt");
                JOptionPane.showMessageDialog(frame, "New staff member added successfully!");
                frame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
