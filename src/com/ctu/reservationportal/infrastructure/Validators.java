package com.ctu.reservationportal.infrastructure;
import java.sql.*;
import java.util.regex.Pattern;

/**
 * Class that contains all the validation methods
 */
public class Validators {
    private static final String URL = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin123$";

    /**
     * Method responsible for the validation of username
     * @param username
     * @return
     */
    public static boolean isValidUsername(String username) {
        // Regular expression to validate username: at least 5 characters, including letters (uppercase or lowercase), and may include special characters and numbers
        String regex = "[\\w\\W]{5,}";
        return Pattern.matches(regex, username) && !existsInDatabase("SELECT COUNT(*) FROM admininfo WHERE username = ?", username, 1);
    }

    /**
     * Method responsible for the validation of email address
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        // Email validation regex to allow uppercase and lowercase letters before @, no digits after @, and require .com at the end
        String emailRegex = "(?i)^[a-z0-9._%+-]+@[a-z.-]+\\.com$";
        return Pattern.matches(emailRegex, email) && !existsInDatabase("SELECT COUNT(*) FROM admininfo WHERE email = ?", email, 1);
    }


    /**
     * Method responsible for the validation of phone number
     * @param phoneNumber
     * @return
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number format: Philippine style starting with +639 and followed by 9 digits
        String phoneRegex = "^(\\+639|09)\\d{9}$";

        if (!Pattern.matches(phoneRegex, phoneNumber)) {
            return false;
        }

        // Normalize phone number
        String normalizedPhoneNumber = phoneNumber;
        if (phoneNumber.startsWith("09")) {
            normalizedPhoneNumber = "+63" + phoneNumber.substring(1);
        }

        return !existsInDatabase("SELECT COUNT(*) FROM admininfo WHERE phoneNumber = ?", normalizedPhoneNumber, 1);
    }

    /**
     * Method responsible for checking if the inputs for username, email, and phone number
     * already exists inside the database
     * @param query
     * @param value
     * @param columnIndex
     * @return
     */
    private static boolean existsInDatabase(String query, String value, int columnIndex) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(columnIndex, value); // Set the parameter based on the given column index
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        System.out.println("It seems like you have provided an existing detail. Provide a new one");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred or entry doesn't exist
    }

    /**
     * Method responsible for the validation of names input
     * @param name
     * @return
     */
    public static boolean isValidName(String name) {
        // Regular expression to match names with only letters (uppercase or lowercase), spaces, and hyphens
        String regex = "^[a-zA-Z]+(?:-[a-zA-Z]+)*(?: [a-zA-Z]+(?:-[a-zA-Z]+)*)*$";
        return Pattern.matches(regex, name);
    }

    /**
     * Method responsible for the validation birthdate
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        // Date format: YYYY-MM-DD
        // Ensure month is between 01 and 12 and day is between 01 and 31
        return Pattern.matches("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[01])", date);
    }

    /**
     * Method responsible for the validation of password
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        // Password must be 8 or more characters long
        // Must contain at least 1 capital letter, 1 lowercase letter, 1 special character, and 1 number
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#^+;:?&\\-_/|]).{8,}$");
    }

    /**
     * Method responsible for the validation of re-entered ID number
     * @param idNumber
     * @return
     */
    public static boolean isValidIDNumber(String idNumber) {
        // Validate the format of the ID number using a regular expression
        return idNumber.matches("\\d{6}"); // Assuming the ID number should be 6 digits
    }

    /**
     * Method responsible for the validation of street input
     * some street name contains dot, hyphen, and digits
     * @param street
     * @return
     */
    public static boolean isValidStreet(String street) {
        // Street format: [Alphanumeric with spaces and special characters]
        return Pattern.matches("[\\w\\d\\s.,'()-]+", street);
    }

    /**
     * Method responsible for the validation of gender input
     * @param gender
     * @return
     */
    public static boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("n") || gender.equalsIgnoreCase("Male")
                || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase ("Not to say") ;
    }

    /**
     * Method responsible for the validation of barangay, municipality, and city
     * @param location
     * @return
     */
    public static boolean isValidLocation(String location) {
        // Location format: Alphabetic characters, spaces, and special characters
        return Pattern.matches("[a-zA-Z\\s.,'()-]+", location);
    }

    /**
     * Method responsible for the validation of ZIP code number
     * @param zipCode
     * @return
     */
    public static boolean isValidZIPCode(String zipCode) {
        // ZIP code format: Four digits
        return zipCode.matches("^\\d{4}$");
    }

    /**
     * Method responsible for the validation of nationality input
     * @param nationality
     * @return
     */
    public static boolean isValidNationality(String nationality) {
        return NationalityValidation.isValidNationality(nationality);
    }

    /**
     * Method responsible for the validation of input based on the type of field provided in fieldName
     * @param fieldName
     * @param userInput
     * @return
     */
    public static boolean isValidInput(String fieldName, String userInput) {
        switch (fieldName) {
            case "First Name":
            case "Middle Name":
            case "Last Name":
            case "Nationality":
            case "Street":
            case "Barangay":
            case "City":
            case "Municipality":
                return Validators.isValidName(userInput);
            case "Birthdate":
                try {
                    return Validators.isValidDate(userInput);
                } catch (Exception e) {
                    return false;
                }
            case "Email":
                try {
                    return Validators.isValidEmail(userInput);
                } catch (Exception e) {
                    return false;
                }
            case "Phone Number":
                try {
                    return Validators.isValidPhoneNumber(userInput);
                } catch (Exception e) {
                    return false;
                }
            case "ZIP code":
                try {
                    return Validators.isValidZIPCode(userInput);
                } catch (Exception e) {
                    return false;
                }
            case "Gender":
                return Validators.isValidGender(userInput);
            default:
                return true;
        }
    }

    public boolean isValid(String input) {
        return true;
    }
}