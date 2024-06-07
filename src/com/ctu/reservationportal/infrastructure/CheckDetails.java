package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.model.AdminInfo;
import java.util.Scanner;

/**
 * Class for checking the provided details of an admin
 */
public class CheckDetails {

    /**
     * Method responsible for checking provided details
     * @param adminInfo which is the AdminInfo object containing the admin details.
     */
    public static void checkDetails(AdminInfo adminInfo) {
        Scanner scanner = new Scanner(System.in);
        boolean detailsConfirmed = false;

        while (!detailsConfirmed) {
            System.out.println("--------------------------------------");
            System.out.println("      DETAILS YOU HAVE PROVIDED      ");
            System.out.println("--------------------------------------");

            // Display admin details for confirmation
            displayAdminDetails(adminInfo);

            // Prompt to choose action
            System.out.println("   Are the details correct?");
            System.out.println("-------------------------------------");
            System.out.println("   1: Edit Details");
            System.out.println("   2: Proceed to Registration Code");
            System.out.println("-------------------------------------");
            System.out.print("Please enter your choice: ");

            // Check if input is an integer
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Edit admin information
                        EditAdminInfo edit = new EditAdminInfo(adminInfo);
                        edit.editAdminInfo();
                        detailsConfirmed = true;
                        break;
                    case 2:
                        // Proceed to generate registration code
                        System.out.println("----------------------------------------------------");
                        System.out.println(" Details confirmed. Proceeding to Registration Code.");
                        RegistrationCode generatedCode = new RegistrationCode();
                        generatedCode.displayRegistrationCode();
                        detailsConfirmed = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter either 1 or 2.");
                        System.out.println(" ");
                }
            } else {
                // If input is not an integer, clear the input buffer and prompt the user again
                System.out.println("Invalid input. Please enter either 1 or 2.");
                System.out.println(" ");
                scanner.nextLine(); // Consume the invalid i                 nput
            }
        }
    }

    /**
     * Helper method to display admin details
     */
    private static void displayAdminDetails(AdminInfo adminInfo) {
        System.out.println("  Username: " + adminInfo.getUsername());
        System.out.println("  Password: " + adminInfo.getPassword());
        System.out.println("  Name: " + adminInfo.getFirstName() + " " + adminInfo.getMiddleName() + " " + adminInfo.getLastName());
        System.out.println("  Birthdate: " + adminInfo.getBirthdate());
        System.out.println("  Email: " + adminInfo.getEmail());
        System.out.println("  Phone Number: " + adminInfo.getPhoneNumber());
        System.out.println("  Home Address: " + adminInfo.getStreet() + ", " + adminInfo.getBarangay() + ", "
                + adminInfo.getMunicipality() + ", " + adminInfo.getCity());
        System.out.println("  ZIP code: " + adminInfo.getZIPcode());
        System.out.println("  Nationality: " + adminInfo.getNationality());
        System.out.println("  Gender: " + adminInfo.getGender());
        System.out.println("  Role at School: " + adminInfo.getRoleAtSchool());
    }
}
