package com.ctu.reservationportal.infrastructure;

import java.util.Random;
import java.util.Scanner;

/**
 * Class handling generating registration code
 */
public class RegistrationCode {
    private static final int CODE_LENGTH = 6;

    /**
     * Generates a random 6-digit registration code
     * @return a random 6-digit registration code
     */
    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Generates each digit of the registration code by appending a random digit (0-9) to the codeBuilder StringBuilder
        // The loop runs for CODE_LENGTH times.
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }

        // Returns the generated registration code as a string
        return codeBuilder.toString();
    }

    /**
     * The method generates a registration code and verifies it against the input.
     */
    public static void displayRegistrationCode() {
        Scanner scanner = new Scanner(System.in);

        boolean codeMatched = false;

        do {
            String code = generateCode();
            System.out.println("Registration Code: " + code);
            System.out.println("-------------------------------------");
            System.out.print("Enter Registration Code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(code)) {
                codeMatched = true;
                System.out.println(" ");
                System.out.println("Registration code is valid.");
            } else {
                System.out.println("Registration code does not match. Please try again.");
                System.out.println(" ");
            }
        } while (!codeMatched);
    }
}
