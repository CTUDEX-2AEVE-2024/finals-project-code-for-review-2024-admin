package com.ctu.reservationportal.dbservices;


import com.ctu.reservationportal.model.AdminInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ctu.reservationportal.dbservices.CreateTables.printSQLException;

public class SelectRecords {
    private static final String SELECT_USERS_SQL = "SELECT * FROM admininfo WHERE username = ?";

    public SelectRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public AdminInfo selectUserRecord(String username) throws SQLException {
        AdminInfo adminInfo= null;
        System.out.println("Selecting user data from DB");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                adminInfo = new AdminInfo();

                adminInfo.setUsername(resultSet.getString("username"));
                adminInfo.setIdNumber(resultSet.getInt("idNumber"));
                adminInfo.setFirstName(resultSet.getString("firstName"));
                adminInfo.setMiddleName(resultSet.getString("middleName"));
                adminInfo.setLastName(resultSet.getString("lastName"));
                adminInfo.setBirthDate(String.valueOf(resultSet.getDate("birthDate").toLocalDate()));
                adminInfo.setStreet(resultSet.getString("street"));
                adminInfo.setBarangay(resultSet.getString("barangay"));
                adminInfo.setMunicipality(resultSet.getString("municipality"));
                adminInfo.setCity(resultSet.getString("city"));
                adminInfo.setZIPcode(resultSet.getInt("zipCode"));
                adminInfo.setEmail(resultSet.getString("Email"));
                adminInfo.setPhoneNumber(String.valueOf(resultSet.getLong("PhoneNumber")));
                adminInfo.setNationality(resultSet.getString("nationality"));
                adminInfo.setGender(resultSet.getString("gender"));
                adminInfo.setRoleAtSchool(resultSet.getString("roleAtSchool"));

            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return adminInfo;
    }

    public static void main(String[] args) {
        SelectRecords selectRecords = new SelectRecords();
        try {
            // Example usage: Select user record with username "johndoe123"
            AdminInfo adminInfo = selectRecords.selectUserRecord("johndoe123");
            if (adminInfo != null) {
                System.out.println("Admin found: " + adminInfo.getFirstName() + " " + adminInfo.getLastName());
            } else {
                System.out.println("Admin not found.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

