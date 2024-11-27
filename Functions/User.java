package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    static List<User> users = loadUsersFromFile("user.txt");
    static List<User> staff = loadStaffFromFile("staff.txt");

    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    // (In Terminal)
    public static void createUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (findUserByUsername(users, username) != null) {
            System.out.println("User already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        User newUser = new User(username, password, email, phoneNumber);
        users.add(newUser);
        System.out.println("User created successfully.");
        writeUserToFile(newUser, "user.txt");
    }

    public static void writeUserToFile(User user, String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhoneNumber() + "\n");
            System.out.println("User data written to " + filename + " successfully.");
        } catch (IOException e) {
            System.out.println("Error writing user data to " + filename + ": " + e.getMessage());
        }
    }

    public static void writeStaffToFile(User staff, String filename) {
    try (FileWriter writer = new FileWriter(filename, true)) {
        writer.write(staff.getUsername() + "," + staff.getPassword() + "," + staff.getEmail() + "," + staff.getPhoneNumber() + "\n");
        System.out.println("Staff data written to " + filename + " successfully.");
    } catch (IOException e) {
        System.out.println("Error writing staff data to " + filename + ": " + e.getMessage());
    }
}

    public static List<User> loadUsersFromFile(String filename) {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); 
                if (parts.length == 4) { 
                    String username = parts[0];
                    String password = parts[1];
                    String email = parts[2];
                    String phoneNumber = parts[3];
                    User user = new User(username, password, email, phoneNumber);
                    users.add(user);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return users;
    }

    public static User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; 
    }

    public static User authenticateUser(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; 
            }
        }
        return null; 
    }

    // In Terminal
    public static void staffFunctionalities(Scanner scanner) {
        System.out.print("Enter staff username: ");
        String staffUsername = scanner.nextLine();
        System.out.print("Enter staff password: ");
        String staffPassword = scanner.nextLine();
        User staffUser = authenticateUser(staff, staffUsername, staffPassword);

        if (staffUser != null) {
            List<Booking> bookings = BookingManager.loadBookingsFromFile("bookings.txt", users, Flight.loadFlightsFromFile("flights.txt"));
            BookingManager bookingManager = new BookingManager(bookings);
            System.out.println("Staff Functionalities:");
            System.out.println("1. View all bookings for a user");
            System.out.println("2. View details of a specific booking");
            System.out.println("3. Add a new staff member");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.println("User's bookings:");
                    BookingManager.viewBookingDetailsusername(username);
                    break;
                case 2:
                    System.out.print("Enter booking ID: ");
                    String bookingId = scanner.nextLine();
                    bookingManager.viewBookingDetails(bookingId);
                    break;
                case 3:
                System.out.print("Enter new staff username: ");
                String newStaffUsername = scanner.nextLine();
                if (findUserByUsername(staff, newStaffUsername) != null) {
                    System.out.println("Staff member already exists.");
                    return;
                }
                System.out.print("Enter new staff password: ");
                String newStaffPassword = scanner.nextLine();
                User newStaff = new User(newStaffUsername, newStaffPassword,"",""); 
                staff.add(newStaff);
                System.out.println("New staff member added successfully.");

                writeStaffToFile(newStaff, "staff.txt");
                break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } else {
            System.out.println("Authentication failed. Invalid staff username or password.");
        }
    }

    // In Terminal
    public static void existingUserFunctionalities(Scanner scanner) {
        List<Booking> bookings = BookingManager.loadBookingsFromFile("bookings.txt", users, Flight.loadFlightsFromFile("flights.txt"));
        BookingManager bookingManager = new BookingManager(bookings);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    
        User currentUser = User.authenticateUser(users, username, password);
    
        if (currentUser != null) {
            System.out.println("Existing User Functionalities:");
            System.out.println("1. View my booking");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Book a ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (choice) {
                case 1:
                    System.out.println("My bookings:");
                    BookingManager.viewBookingDetailsusername(username);
                    break;
                case 2:
                    System.out.print("Enter booking ID to cancel: ");
                    String cancelBookingId = scanner.nextLine();
                    bookingManager.cancelBooking(cancelBookingId);
                    break;
                case 3:
                    Booking.booking(bookingManager, scanner, currentUser); 
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
            }
        } else {
            System.out.println("Authentication failed. Invalid username or password.");
        }
    }

    public static List<User> loadStaffFromFile(String filename) {
        List<User> staffList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); 
                if (parts.length == 2) { 
                    String username = parts[0];
                    String password = parts[1];
                    User staff = new User(username, password,"", ""); 
                    staffList.add(staff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Staff file not found: " + filename);
        }
        return staffList;
    }
}
