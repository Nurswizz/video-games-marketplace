package src.utils;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Utility class for reading and validating user input from console
 */
public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a line of text input
     * @return trimmed input string
     */
    public String readLine() {
        return scanner.nextLine().trim();
    }

    /**
     * Reads and parses an integer
     * @return parsed integer or null if invalid
     */
    public Integer readInteger() {
        try {
            String input = readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }



    /**
     * Reads and parses a long
     * @return parsed long or null if invalid
     */
    public Long readLong() {
        try {
            String input = readLine();
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }

    /**
     * Reads and parses a float
     * @return parsed float or null if invalid
     */
    public Float readFloat() {
        try {
            String input = readLine();
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }



    /**
     * Reads a date from user input (year, month, day)
     * @return LocalDate or null if invalid
     */
    public String readDate() {
        System.out.print("Year: ");
        Integer year = readInteger();
        if (year == null) return null;

        System.out.print("Month (1-12): ");
        Integer month = readInteger();
        if (month == null || !Validation.isValidMonth(month)) {
            System.out.println("Invalid month. Must be between 1 and 12.");
            return null;
        }

        System.out.print("Day (1-31): ");
        Integer day = readInteger();
        if (day == null || !Validation.isValidDay(day)) {
            System.out.println("Invalid day. Must be between 1 and 31.");
            return null;
        }

        try {
            return Transformer.fromIntToReleaseDate(month, day, year);
        } catch (Exception e) {
            System.out.println("Invalid date: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads a yes/no confirmation
     * @param prompt the confirmation prompt
     * @return true if user confirms (yes), false otherwise
     */
    public boolean readConfirmation(String prompt) {
        System.out.print(prompt + " (yes/no): ");
        String input = readLine();
        return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
    }



    /**
     * Reads input until a non-empty string is provided
     * @param fieldName name of the field for error message
     * @return non-empty string
     */
    public String readNonEmpty(String fieldName) {
        String input;
        do {
            input = readLine();
            if (input.isEmpty()) {
                System.out.printf("%s cannot be empty. Please try again: ", fieldName);
            }
        } while (input.isEmpty());
        return input;
    }

    public String readWithDefault(String label, String currentValue) {
        System.out.print(label + " [" + currentValue + "]: ");
        String input = readLine();
        return input.isBlank() ? currentValue : input;
    }

    public int readIntWithDefault(String label, int currentValue) {
        System.out.print(label + " [" + currentValue + "]: ");
        String input = readLine();
        return input.isBlank() ? currentValue : Integer.parseInt(input);
    }

    public float readFloatWithDefault(String label, float currentValue) {
        System.out.print(label + " [" + currentValue + "]: ");
        String input = readLine();
        return input.isBlank() ? currentValue : Float.parseFloat(input);
    }



}