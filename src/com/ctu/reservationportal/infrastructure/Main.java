package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.infrastructure.CreateAdminInfo;
import com.ctu.reservationportal.model.AdminInfo;
import com.ctu.reservationportal.dbservices.InsertRecords;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class
 */
public class Main {

    /**
     * Main method
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("│──────────────────────────────────────────│");
            System.out.println("│              ADMINISTRATOR               │");
            System.out.println("│                MANAGEMENT                │");
            System.out.println("│──────────────────────────────────────────│");
            System.out.println("│  How can we be of service to you today?  │");
            System.out.println("│         1. Register Account              │");
            System.out.println("│         2. Search and Retrieve           │");
            System.out.println("│         3. Update Details                │");
            System.out.println("│         4. Exit                          │");
            System.out.println("│──────────────────────────────────────────│");


            int choice = -1;
            while (choice < 1 || choice > 4) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (choice) {
                        case 1:
                            // Creating an instance of CreateAdminInfo class
                            CreateAdminInfo registerAdminService = new CreateAdminInfo();

                            // Start the registration process
                            registerAdminService.registerAdmin();
                            break;
                    case 2:
                            SearchAndRetrieve.searchAndRetrieve();
                            break;
                        case 3:
                            AdminInfo admin = new AdminInfo();

                            // Create an instance of UpdateAdminInfo class
                            UpdateAdminInfo updateAdminService = new UpdateAdminInfo();

                            // Call the promptAndUpdate method with the admin object
                            updateAdminService.promptAndUpdate();
                            break;
                        case 4:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 to 4 only.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
        }

        // Close the scanner
        scanner.close();
        System.out.println("Thank you!");
    }
}
