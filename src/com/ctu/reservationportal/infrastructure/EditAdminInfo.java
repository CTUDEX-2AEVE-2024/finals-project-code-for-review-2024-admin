package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.model.AdminInfo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for editing your details
 */
public class EditAdminInfo {
    private final Scanner scanner;
    private final AdminInfo adminRegistration;
    private final Validators validators;
    private boolean exitEditing;

    /**
     * This constructor sets up the necessary components
     * (like the Scanner for input, validators for data validation, and an exit flag)
     * @param adminRegistration
     */
    public EditAdminInfo(AdminInfo adminRegistration) {
        this.adminRegistration = adminRegistration;
        this.scanner = new Scanner(System.in);
        this.validators = new Validators();
        this.exitEditing = false; // Initializing the exitEditing flag
    }

    /**
     * This method handles editing operations for admin information
     */
    public void editAdminInfo() {
        do {
            System.out.println("──────────────────────────────────────────");
            System.out.println("         EDIT ADMIN INFORMATION");
            System.out.println("──────────────────────────────────────────");

            // Print options for editing
            printEditOptions();

            System.out.print("Enter your choice (0 to exit editing): ");
            System.out.println(" ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 0:
                        System.out.println("Exit editing process.");
                        RegistrationCode.displayRegistrationCode(); // Display registration code
                        exitEditing = true;
                        break;
                    case 1:
                        editField("Username", this::editUserName);
                        break;
                    case 2:
                        editField("Password", this::editPassword);
                        break;
                    case 3:
                        editField("First Name", this::editFirstName);
                        break;
                    case 4:
                        editField("Middle Name", this::editMiddleName);
                        break;
                    case 5:
                        editField("Last Name", this::editLastName);
                        break;
                    case 6:
                        editField("Birthdate", this::editBirthdate);
                        break;
                    case 7:
                        editField("Email", this::editEmail);
                        break;
                    case 8:
                        editField("Phone Number", this::editPhoneNumber);
                        break;
                    case 9:
                        editField("Street", this::editStreet);
                        break;
                    case 10:
                        editField("Barangay", this::editBarangay);
                        break;
                    case 11:
                        editField("City", this::editCity);
                        break;
                    case 12:
                        editField("Municipality", this::editMunicipality);
                        break;
                    case 13:
                        editField("ZIP code", this::editZIPCode);
                        break;
                    case 14:
                        editField("Gender", this::editGender);
                        break;
                    case 15:
                        editField("Nationality", this::editNationality);
                        break;
                    case 16:
                        editField("Role at School", this::editRoleAtSchool);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        } while (!exitEditing); // Continue editing until flag is set
    }

    /**
     * Method to print edit options
     */
    private void printEditOptions() {
        System.out.println("    What information do you want to edit?");
        System.out.println("--------------------------------------------");
        System.out.println("    1. Username");
        System.out.println("    2. Password");
        System.out.println("    3. First Name");
        System.out.println("    4. Middle Name");
        System.out.println("    5. Last Name");
        System.out.println("    6. Birthdate");
        System.out.println("    7. Email");
        System.out.println("    8. Phone Number");
        System.out.println("    9. Street");
        System.out.println("    10. Barangay");
        System.out.println("    11. City");
        System.out.println("    12. Municipality");
        System.out.println("    13. ZIP code");
        System.out.println("    14. Gender");
        System.out.println("    15. Nationality");
        System.out.println("    16. Role at School");
        System.out.println("    0. Exit Editing");
    }

    /**
     * A method used for editing a specific field of admin information
     * @param fieldName
     * @param editMethod
     */
    private void editField(String fieldName, Runnable editMethod) {
        System.out.println("\nEditing " + fieldName);
        editMethod.run();
        System.out.println(fieldName + " updated successfully!\n");

        adminRegistration.displayAdminInfo();

        boolean validChoice = false;
        do {
            System.out.print("Do you want to continue editing? (1 = Yes, 0 = No): ");
            System.out.println(" ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    exitEditing = true;
                    RegistrationCode.displayRegistrationCode();
                    return;
                } else if (choice == 1) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid input. Please enter either 1 or 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter either 1 or 0.");
            }
        } while (!validChoice);
    }

    private String validateInput(String fieldName) {
        String userInput;
        boolean isValid;

        do {
            System.out.print("Enter new " + fieldName + ": ");
            userInput = scanner.nextLine();
            isValid = Validators.isValidInput(fieldName, userInput);

            if (!isValid) {
                System.out.println("Invalid input. Please try again.");
                System.out.println(" ");
                System.out.println(" ");
            }
        } while (!isValid);
        return userInput;
    }


    /**
     * method is responsible for editing username of the adminRegistration object
     */
    private void editUserName() {
        adminRegistration.setUsername(validateInput("Username"));
    }

    /**
     * method is responsible for editing password of the adminRegistration object
     */
    private void editPassword() {
        adminRegistration.setPassword(validateInput("Passsword"));
    }

    /**
     * method is responsible for editing first name of the adminRegistration object
     */
    private void editFirstName() {
        adminRegistration.setFirstName(validateInput("First Name"));
    }

    /**
     * method is responsible for editing middle name of the adminRegistration object
     */
    private void editMiddleName() {
        adminRegistration.setMiddleName(validateInput("Middle Name"));
    }

    /**
     * method is responsible for editing last name of the adminRegistration object
     */
    private void editLastName() {
        adminRegistration.setLastName(validateInput("Last Name"));
    }

    /**
     * method responsible for editing birthdate of the adminRegistration object
     */
    private void editBirthdate() {
        adminRegistration.setBirthDate(validateInput("Birthdate"));
    }

    /**
     * method responsible for editing email of the adminRegistration object
     */
    private void editEmail() {
        adminRegistration.setEmail(validateInput("Email"));
    }

    /**
     * method responsible for editing phoneNumber of the adminRegistration object
     */
    private void editPhoneNumber() {
        adminRegistration.setPhoneNumber(validateInput("Phone Number"));
    }

    /**
     * method responsible for editing street of the adminRegistration object
     */
    private void editStreet() {
        adminRegistration.setStreet(validateInput("Street"));
    }

    /**
     * method responsible for editing barangay of the adminRegistration object
     */
    private void editBarangay() {
        adminRegistration.setBarangay(validateInput("Barangay"));
    }

    /**
     * method responsible for editing city of the adminRegistration object
     */
    private void editCity() {
        adminRegistration.setCity(validateInput("City"));
    }

    /**
     * method responsible for editing municipality of the adminRegistration object
     */
    private void editMunicipality() {
        adminRegistration.setMunicipality(validateInput("Municipality"));
    }

    /**
     * method responsible for editing ZIPcode of the adminRegistration object
     */
    private void editZIPCode() {
        adminRegistration.setZIPcode(Integer.parseInt(validateInput("ZIP code")));
    }

    /**
     * method responsible for editing gender of the adminRegistration object
     */
    private void editGender() {
        adminRegistration.setGender(validateInput("Gender"));
    }

    /**
     * method responsible for editing nationality of the adminRegistration object
     */
    private void editNationality() {
        adminRegistration.setNationality(validateInput("Nationality"));
    }

    /**
     * method responsible for editing roleAtSchool of the adminRegistration object
     */
    private void editRoleAtSchool() {
        adminRegistration.setRoleAtSchool(validateInput("Role at School"));
    }

//    private String validateInput(String fieldName) {
//        String userInput;
//        boolean isValid;
//
//        do {
//            System.out.print("Enter new " + fieldName + ": ");
//            userInput = scanner.nextLine();
//            isValid = validators.isValidInput(fieldName, userInput);
//
//            if (!isValid) {
//                System.out.println("Invalid input. Please try again.");
//                System.out.println(" ");
//            }
//        } while (!isValid);
//        return userInput;
//    }
}
