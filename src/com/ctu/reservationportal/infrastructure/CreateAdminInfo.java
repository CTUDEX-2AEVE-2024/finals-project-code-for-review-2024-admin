package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.dbservices.InsertRecords;
import com.ctu.reservationportal.model.AdminInfo;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Class that contains processes for creating and inserting admin info unto the database
 */
public class CreateAdminInfo {

    /**
     * Prompts the user for input and validates it using a provided validator
     * @param scanner       Scanner object for input
     * @param promptMessage Message to display when prompting for input
     * @param validator     Validator for input validation
     * @return Validated user input
     */
    public static String promptForInput(Scanner scanner, String promptMessage, CreateAdminInfo.Validator validator) {
        String input;
        boolean isValid;
        do {
            System.out.print(promptMessage);
            input = scanner.nextLine();
            isValid = validator.isValid(input);
            if (!isValid) {
                System.out.println("Invalid input. Please try again.");
                System.out.println(" ");
            }
        } while (!isValid);
        return input;
    }

    // Interface for input validation
    interface Validator {
        // Any class that implements the Validator interface must provide an implementation for the isValid method
        boolean isValid(String input);
    }

    private Scanner scanner;

    // Constructor to initialize the newly created object
    public CreateAdminInfo() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This registerAdmin method has an object AdminInfo
     */
    public void registerAdmin() {
        while (true) {
            AdminInfo adminRegistration = new AdminInfo();
            int adminChoice = 0;

            do {
                System.out.print("Are you an administrator? (1 = Yes, 2 = No): ");
                try {
                    adminChoice = Integer.parseInt(scanner.nextLine().trim());
                    if (adminChoice != 1 && adminChoice != 2) {
                        System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value (1 or 2).");
                }
            } while (adminChoice != 1 && adminChoice != 2);

            if (adminChoice == 2) {
                System.out.println("This is for admin registration only.");
                System.exit(0);
            }

            // Admin registration process continues here
            System.out.println("-----------------------------------------------");
            System.out.println("                ADMIN REGISTRATION              ");
            System.out.println("-----------------------------------------------");
            System.out.println("Ensure that every name input does not contain any digits, " +
                    "otherwise it won't be accepted.\n" +
                    "Follow given formats for numbers.\n" +
                    "Input NONE if the field is not applicable with you.");
            System.out.println(" ");

            String username = promptForInput(scanner, "Enter Username: ", Validators::isValidUsername);
            adminRegistration.setUsername(username);

            String password = promptForInput(scanner, "Enter Password: ", Validators::isValidPassword);
            adminRegistration.setPassword(password);

            int idNumber = IDGenerator.generateUserID();
            IDGenerator.displayID(idNumber);

            int enteredIdNumber;
            do {
                enteredIdNumber = Integer.parseInt(promptForInput(scanner, "Enter ID Number: ", Validators::isValidIDNumber));
                if (enteredIdNumber != idNumber) {
                    System.out.println("ID number does not match. Please try again.");
                }
            } while (enteredIdNumber != idNumber);
            adminRegistration.setIdNumber(idNumber);

            String firstName = promptForInput(scanner, "Enter First name: ", Validators::isValidName);
            adminRegistration.setFirstName(firstName);

            String middleName = promptForInput(scanner, "Enter Middle name: ", Validators::isValidName);
            adminRegistration.setMiddleName(middleName);

            String lastName = promptForInput(scanner, "Enter Last name: ", Validators::isValidName);
            adminRegistration.setLastName(lastName);

            String birthdate = promptForInput(scanner, "Enter Birthdate (YYYY-MM-DD): ", Validators::isValidDate);
            adminRegistration.setBirthDate(birthdate);

            String email = promptForInput(scanner, "Enter Email: ", Validators::isValidEmail);
            adminRegistration.setEmail(email);

            String phoneNumber = promptForInput(scanner, "Enter Phone Number: ", Validators::isValidPhoneNumber);
            adminRegistration.setPhoneNumber(phoneNumber);

            String street = promptForInput(scanner, "Enter Street: ", Validators::isValidStreet);
            adminRegistration.setStreet(street);

            String barangay = promptForInput(scanner, "Enter Barangay: ", Validators::isValidLocation);
            adminRegistration.setBarangay(barangay);

            String municipality = promptForInput(scanner, "Enter Municipality: ", Validators::isValidLocation);
            adminRegistration.setMunicipality(municipality);

            String city = promptForInput(scanner, "Enter City: ", Validators::isValidLocation);
            adminRegistration.setCity(city);

            int zipcode = Integer.parseInt(promptForInput(scanner, "Enter ZIP Code: ", Validators::isValidZIPCode));
            adminRegistration.setZIPcode(zipcode);

            String nationality = promptForInput(scanner, "Enter Nationality: ", Validators::isValidNationality);
            adminRegistration.setNationality(nationality);

            String gender = promptForInput(scanner, "Enter Gender (M-Male|F-Female|N-Not to say): ", Validators::isValidGender);
            adminRegistration.setGender(gender);

            int roleChoice;
            boolean validChoice = false;
            do {
                System.out.print("\nEnter role at school (1 for Teacher, 2 for Student, 3 for Staff): ");
                String input = scanner.nextLine();
                try {
                    roleChoice = Integer.parseInt(input);
                    switch (roleChoice) {
                        case 1:
                            adminRegistration.setRoleAtSchool("Teacher");
                            validChoice = true;
                            break;
                        case 2:
                            adminRegistration.setRoleAtSchool("Student");
                            validChoice = true;
                            break;
                        case 3:
                            adminRegistration.setRoleAtSchool("Staff");
                            validChoice = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1 for Teacher, 2 for Student, or 3 for Staff.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            } while (!validChoice);

            // Call Check user detail
            CheckDetails.checkDetails(adminRegistration);

            // End the program after all actions are completed
            System.out.println("You have successfully registered.");

            // Instantiate the InsertRecords class
            InsertRecords insertRecords = new InsertRecords();

            // Call the insertUserRecord method of InsertRecords and pass the adminRegistration object
            insertRecords.insertAdminRecord(adminRegistration);

            // Ask if the user wants to register another admin
            boolean isValidChoice = false;
            while (!isValidChoice) {
                System.out.print("Do you want to register another admin account (1 for Yes, 2 for No)? ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice == 1) {
                        isValidChoice = true;
                        // Re-register admin
                    } else if (choice == 2) {
                        isValidChoice = true;
                        System.out.println("Exiting admin registration.");
                        return; // Exit the method if the user chooses not to register another account
                    } else {
                        System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                        System.out.println(" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer (1 for Yes, 2 for No).");
                    System.out.println(" ");
                }
            }
        }
    }
}
