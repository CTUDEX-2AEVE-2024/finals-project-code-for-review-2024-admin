package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.model.AdminInfo;
import java.sql.*;
import java.util.Scanner;

import static com.ctu.reservationportal.infrastructure.Validators.isValidInput;

/**
 * Class that holds the execution for admin to update their information
 */
public class UpdateAdminInfo {
    /**
     * Prompts for username and password
     */
    public void promptAndUpdate() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter username and password
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Create an AdminInfo object with the provided username and password
        AdminInfo admin = new AdminInfo();
        admin.setUsername(username);
        admin.setPassword(password);

        // Check if admin exists in the database
        AdminInfo foundAdmin = getAdminDetailsFromDatabase(admin);

        if (foundAdmin != null) {

            // Admin found, display details
            displayAdminDetails(foundAdmin);

            // Prompt for update
            promptUpdateFields(foundAdmin);
        } else {
            // Admin not found
            System.out.println("    Admin not found.");
            System.out.println(" ");
        }
    }

    /**
     * Retrieving details from the database
     * @param admin
     * @return
     */
    private AdminInfo getAdminDetailsFromDatabase(AdminInfo admin) {
        // Establishing a connection to a MySQL database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "admin123$")) {

          // Query for searching inside the database using username and password
            String selectQuery = "SELECT * FROM admininfo WHERE username = ? AND password = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, admin.getUsername());
                selectStatement.setString(2, admin.getPassword());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Admin found, construct and return AdminInfo object
                        AdminInfo updatedAdmin = new AdminInfo();
                        updatedAdmin.setUsername(resultSet.getString("username"));
                        updatedAdmin.setPassword(resultSet.getString("password"));
                        updatedAdmin.setFirstName(resultSet.getString("firstName"));
                        updatedAdmin.setMiddleName(resultSet.getString("middleName"));
                        updatedAdmin.setLastName(resultSet.getString("lastName"));
                        updatedAdmin.setBirthDate(resultSet.getString("birthdate"));
                        updatedAdmin.setEmail(resultSet.getString("email"));
                        updatedAdmin.setPhoneNumber(resultSet.getString("phoneNumber"));
                        updatedAdmin.setStreet(resultSet.getString("street"));
                        updatedAdmin.setBarangay(resultSet.getString("barangay"));
                        updatedAdmin.setCity(resultSet.getString("city"));
                        updatedAdmin.setMunicipality(resultSet.getString("municipality"));
                        updatedAdmin.setZIPcode(resultSet.getInt("zipcode"));
                        updatedAdmin.setGender(resultSet.getString("gender"));
                        updatedAdmin.setNationality(resultSet.getString("nationality"));
                        updatedAdmin.setRoleAtSchool(resultSet.getString("roleAtSchool"));
                        return updatedAdmin;
                    } else {
                        // Admin not found, return null
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void displayAdminDetails(AdminInfo admin) {
        System.out.println("--------------------------------------");
        System.out.println("           ADMIN DETAILS:");
        System.out.println("--------------------------------------");
        System.out.println("  Username: " + admin.getUsername());
        System.out.println("  Password: " + admin.getPassword());
        System.out.println("  First Name: " + admin.getFirstName());
        System.out.println("  Middle Name: " + admin.getMiddleName());
        System.out.println("  Last Name: " + admin.getLastName());
        System.out.println("  Birthdate: " + admin.getBirthdate());
        System.out.println("  Email: " + admin.getEmail());
        System.out.println("  Phone Number: " + admin.getPhoneNumber());
        System.out.println("  Street: " + admin.getStreet());
        System.out.println("  Barangay: " + admin.getBarangay());
        System.out.println("  Municipality: " + admin.getMunicipality());
        System.out.println("  City: " + admin.getCity());
        System.out.println("  ZIP Code: " + admin.getZIPcode());
        System.out.println("  Nationality: " + admin.getNationality());
        System.out.println("  Gender: " + admin.getGender());
        System.out.println("  Role at School: " + admin.getRoleAtSchool());
        System.out.println("--------------------------------------");
    }

    private void promptUpdateFields(AdminInfo admin) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("    Choose a field to update:");
            System.out.println("--------------------------------------");
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
            System.out.println("    13. ZIP Code");
            System.out.println("    14. Gender");
            System.out.println("    15. Nationality");
            System.out.println("    16. Role at School");
            System.out.println("    0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 0) {
                exit = true;
            } else if (choice >= 1 && choice <= 16) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Enter the new value: ");
                    String newValue = scanner.nextLine();

                    if (isValidInput(String.valueOf(choice), newValue)) {
                        updateDetails(choice, newValue, admin);
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                        System.out.println("");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please choose a valid option.");
                System.out.println("");
            }
        }
    }

    public void updateDetails(int choice, String newValue, AdminInfo admin) {
        String columnName = null;
        boolean isValidInput = false;

        // Convert newValue to lowercase
        newValue = newValue.toLowerCase();

        // Capitalize the first letter
        newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);


        do {
            switch (choice) {
                case 1:
                    columnName = "username";
                    if (!Validators.isValidUsername(newValue)) {
                        System.out.println("Invalid username format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 2:
                    columnName = "password";
                    if (!Validators.isValidPassword(newValue)) {
                        System.out.println("Invalid password format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 3:
                    columnName = "firstName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid first name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 4:
                    columnName = "middleName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid middle name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 5:
                    columnName = "lastName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid last name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 6:
                    columnName = "birthDate";
                    if (!Validators.isValidDate(newValue)) {
                        System.out.println("Invalid birthdate format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 7:
                    columnName = "email";
                    if (!Validators.isValidEmail(newValue)) {
                        System.out.println("Invalid email format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 8:
                    columnName = "phoneNumber";
                    if (!Validators.isValidPhoneNumber(newValue)) {
                        System.out.println("Invalid phone number format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 9:
                    columnName = "street";
                    if (!Validators.isValidStreet(newValue)) {
                        System.out.println("Invalid street format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 10:
                    columnName = "barangay";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid barangay format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 11:
                    columnName = "city";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid city format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 12:
                    columnName = "municipality";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid municipality format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 13:
                    columnName = "ZIPcode";
                    if (!Validators.isValidZIPCode(newValue)) {
                        System.out.println("Invalid ZIP code format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 14:
                    columnName = "gender";
                    String lowerCaseValue = newValue.toLowerCase(); // Convert to lowercase for easier comparison
                    String gender;
                    switch (lowerCaseValue) {
                        case "f":
                        case "female":
                            gender = "Female";
                            isValidInput = true;
                            break;
                        case "m":
                        case "male":
                            gender = "Male";
                            isValidInput = true;
                            break;
                        case "n":
                        case "not to say":
                            gender = "Not to say";
                            isValidInput = true;
                            break;
                        default:
                            System.out.println("Invalid gender format.");
                            return; // Exit the switch statement if input is invalid
                    }
                    newValue = gender;
                    break;

                case 15:
                    columnName = "nationality";
                    if (!Validators.isValidNationality(newValue)) {
                        System.out.println("Invalid nationality format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 16:
                    columnName = "roleAtSchool";
                    if (!newValue.equalsIgnoreCase("Teacher") && !newValue.equalsIgnoreCase("Student") && !newValue.equalsIgnoreCase("Staff")) {
                        System.out.println("Invalid role at school. Please enter 'teacher', 'student', or 'staff'.");
                        System.out.println("");
                        break;
                    } else {
                        // Convert the first character to uppercase and the rest to lowercase
                        newValue = newValue.substring(0, 1).toUpperCase() + newValue.substring(1).toLowerCase();
                        isValidInput = true;
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
                    System.out.println("");
                    return;
            }

            if (!isValidInput) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the new value again: ");
                newValue = scanner.nextLine().toLowerCase(); // Convert to lowercase
                newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1); // Capitalize first letter
            }

        } while (!isValidInput);

        // Update logic
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "admin123$")) {

            // First, retrieve the admin details from the database
            String selectQuery = "SELECT * FROM admininfo WHERE username = ? AND password = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, admin.getUsername());
                selectStatement.setString(2, admin.getPassword());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Display existing admin details...
                    } else {
                        System.out.println(" ");
                        System.out.println("Admin not found.");
                        return;
                    }
                }
            }

            // Then, update the specified field in the admininfo table for the matching rows
            String updateQuery = "UPDATE admininfo SET " + columnName + " = ? WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newValue);
                preparedStatement.setString(2, admin.getUsername());
                preparedStatement.setString(3, admin.getPassword());

                  // Execute the update query...
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(" ");
                    System.out.println("Detail updated successfully.");
                    System.out.println(" ");

                    // Retrieve and display all updated details from the database
                    AdminInfo updatedAdmin = getAdminDetailsFromDatabase(admin);
                    if (updatedAdmin != null) {
                        displayAdminDetails(updatedAdmin);
                    }
                } else {
                    System.out.println("Failed to update the detail.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

