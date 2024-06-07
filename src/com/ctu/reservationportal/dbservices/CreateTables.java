package com.ctu.reservationportal.dbservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 * @author Ramesh Fadatare
 * Contains SQL statement to create tables inside the database
 */
public class CreateTables {

    private static final String createUserInfoTbl = "CREATE TABLE userinfo (" +
            "firstName VARCHAR(255)," +
            "middleName VARCHAR(255)," +
            "lastName VARCHAR(255)," +
            "birthDate DATE," +
            "email VARCHAR(255)," +
            "phoneNumber VARCHAR(255)," +
            "nationality VARCHAR(255)," +
            "gender ENUM('Male', 'Female', 'Not to say')," +
            "roleAtSchool VARCHAR(255)" +
            ");";

    private static final String createRoomRecordTbl = "CREATE TABLE RoomRecord (\n" +
            "    roomNumber VARCHAR(255),\n" +
            "    capacity INT,\n" +
            "    roomStatus BOOLEAN,\n" +
            "    buildingLocation VARCHAR(255),\n" +
            "    maintenanceNotes TEXT,\n" +
            "    roomType VARCHAR(255),\n" +
            "    hasProjector BOOLEAN\n" +
            ");";

    private static final String createBookingDetailsTbl = "CREATE TABLE BookingDetails (\n" +
            "    userName VARCHAR(255),\n" +
            "    email VARCHAR(255),\n" +
            "    date DATE,\n" +
            "    time TIME,\n" +
            "    room VARCHAR(255),\n" +
            "    bookingID INT\n" +
            ");";

    private static final String createAdminInfoTbl = "CREATE TABLE AdminInfo (\n" +
            "username VARCHAR(255)," +
            "password VARCHAR(255)," +
            "idNumber int," +
            "firstName VARCHAR(255)," +
            "middleName VARCHAR(255)," +
            "lastName VARCHAR(255)," +
            "birthDate DATE," +
            "email VARCHAR(255)," +
            "phoneNumber VARCHAR(255)," +
            "street VARCHAR(255)," +
            "barangay VARCHAR(255)," +
            "municipality VARCHAR(255)," +
            "city VARCHAR(255)," +
            "ZIPcode int," +
            "nationality VARCHAR(255)," +
            "gender ENUM('Male', 'Female', 'Not to say')," +
            "roleAtSchool VARCHAR(255)" +
            ");";

    /**
     * A constructor method
     * responsible for creating the required tables in a MySQL database
     */
    public CreateTables(){
        try {
            //This statement attempts to load the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            createTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JBCD MySQL Driver", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A Method responsible for establishing a connection to a MySQL database and
     * creating multiple tables within that database
     * @throws SQLException
     */
    public void createTable() throws SQLException {

        System.out.println("Establishing connection to DB");

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "admin123$");

             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            System.out.println("Creating UserInfo Table in DB");
            statement.execute(createUserInfoTbl);

            System.out.println("Creating RoomRecord Table in DB");
            statement.execute(createRoomRecordTbl);

            System.out.println("Creating BookingDetails Table in DB");
            statement.execute(createBookingDetailsTbl);

            System.out.println("Creating AdminInfo Table in DB");
            statement.execute(createAdminInfoTbl);

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    /**
     * Method to handle and print details about SQL exceptions that occur during database operations
     * @param ex
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
