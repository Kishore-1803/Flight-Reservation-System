import Functions.*;
import java.util.*;

public class ARS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("             Welcome to BLUERIDGE AIRLINES            ");
            System.out.println("------------------------------------------------------");
            System.out.println("1. Staff");
            System.out.println("2. Existing User");
            System.out.println("3. New User");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    User.staffFunctionalities(scanner);
                    break;
                case 2:
                    User.existingUserFunctionalities(scanner);
                    break;
                case 3:
                    User.createUser(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
