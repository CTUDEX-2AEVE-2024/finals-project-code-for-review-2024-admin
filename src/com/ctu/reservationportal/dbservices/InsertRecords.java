package com.ctu.reservationportal.dbservices;

import com.ctu.reservationportal.model.AdminInfo;

import java.sql.*;
import static java.sql.Date.valueOf;

/**
 * The class responsible for handling records inserting
 */
public class InsertRecords {

    // defines an SQL statement for inserting records into a table named admininfo
    private static final String INSERT_USERS_SQL = "INSERT INTO admininfo (" +
            "username, password, idNumber, firstName, middleName, lastName, birthDate, email, phoneNumber, " +
            "street, barangay, municipality, city, ZIPcode, nationality, gender, roleAtSchool) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    /**
     * A constructor that initializes the JDBC URL, username, and password required for
     * establishing a connection to the MySQL database
     */
    public InsertRecords() {
        this.jdbcURL = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
        this.jdbcUsername = "root";
        this.jdbcPassword = "admin123$";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    /**
     * This method is used to insert a new record into the admininfo table of the database
     * @param adminInfo
     */
    public void insertAdminRecord(AdminInfo adminInfo) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, adminInfo.getUsername());
            preparedStatement.setString(2, adminInfo.getPassword());
            preparedStatement.setInt(3, adminInfo.getIdNumber());
            preparedStatement.setString(4, capitalizeFirstLetter(adminInfo.getFirstName()));
            preparedStatement.setString(5, capitalizeFirstLetter(adminInfo.getMiddleName()));
            preparedStatement.setString(6,capitalizeFirstLetter (adminInfo.getLastName()));
            preparedStatement.setString(7, adminInfo.getBirthdate());
            preparedStatement.setString(8, adminInfo.getEmail().toLowerCase());
            preparedStatement.setString(9, formatPhoneNumber(adminInfo.getPhoneNumber()));
            preparedStatement.setString(10, capitalizeFirstLetter(adminInfo.getStreet()));
            preparedStatement.setString(11, capitalizeFirstLetter(adminInfo.getBarangay()));
            preparedStatement.setString(12, capitalizeFirstLetter(adminInfo.getMunicipality()));
            preparedStatement.setString(13, capitalizeFirstLetter(adminInfo.getCity()));
            preparedStatement.setInt(14, adminInfo.getZIPcode());
            preparedStatement.setString(15, capitalizeFirstLetter(adminInfo.getNationality()));
            preparedStatement.setString(16, formatGender(adminInfo.getGender()));
            preparedStatement.setString(17, adminInfo.getRoleAtSchool());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" ");
                System.out.println("A new admin was inserted successfully!");
                System.out.println(" ");
            } else {
                System.out.println("Failed to insert user data.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting user data to DB: " + e.getMessage());
        }
    }

    /**
     * This method ensures that the first letter of a string is capitalized
     * which is useful for formatting data befor storing it in the database
     * @param str
     * @return
     */
    private String capitalizeFirstLetter(String str) {
        // Check if the string is null or empty
        if (str == null || str.isEmpty()) {
            return str;
        }

        // Split the string into words
        String[] words = str.split("\\s+");
        StringBuilder capitalizedStr = new StringBuilder();

        // Capitalize the first letter of each word and convert the rest to lowercase
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Trim the trailing space and return the result
        return capitalizedStr.toString().trim();
    }

    /**
     * A method to standardize gender inputs by accepting a gender string,
     * converting it to lowercase, and then returning the properly formatted gender
     * @param gender
     * @return
     */
    private String formatGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return gender;
        }

        gender = gender.toLowerCase();
        switch (gender) {
            case "f":
            case "female":
                return "Female";
            case "m":
            case "male":
                return "Male";
            case "n":
            case "not to say":
                return "Not to say";
            default:
                throw new IllegalArgumentException("Invalid gender value");
        }
    }

    /**
     * This method helps ensure that phone numbers are standardized to a specific format within the application
     * @param phoneNumber
     * @return
     */
    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return  phoneNumber;
        }
        if (phoneNumber.startsWith("09")) {
            return "+63" + phoneNumber.substring(1);
        } else if (phoneNumber.startsWith("+639")) {
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    /**
     * This method creates an AdminInfo object
     * with sample data and inserts this information into a database
     * @param args
     */
    public static void main(String[] args) {
        // Create a new instance of UserInfo with sample data
        AdminInfo adminInfo = new AdminInfo();

        adminInfo.setUsername(adminInfo.getUsername());
        adminInfo.setPassword(adminInfo.getPassword());
        adminInfo.setIdNumber(adminInfo.getIdNumber());
        adminInfo.setFirstName(adminInfo.getFirstName());
        adminInfo.setMiddleName(adminInfo.getMiddleName());
        adminInfo.setLastName(adminInfo.getLastName());
        adminInfo.setBirthDate(String.valueOf(Date.valueOf(adminInfo.getBirthdate())));
        adminInfo.setEmail(adminInfo.getEmail());
        adminInfo.setPhoneNumber(String.valueOf(adminInfo.getPhoneNumber()));
        adminInfo.setStreet(adminInfo.getStreet());
        adminInfo.setBarangay(adminInfo.getBarangay());
        adminInfo.setMunicipality(adminInfo.getMunicipality());
        adminInfo.setCity(adminInfo.getCity());
        adminInfo.setZIPcode(adminInfo.getZIPcode());
        adminInfo.setNationality(adminInfo.getNationality());
        adminInfo.setGender(adminInfo.getGender());
        adminInfo.setRoleAtSchool(adminInfo.getRoleAtSchool());

        // Create an instance of InsertRecords
        InsertRecords insertRecords = new InsertRecords();

        // Call insertUserRecord method to insert the user info into the database
        insertRecords.insertAdminRecord(adminInfo);
    }
}
