package src.utils;

/**
 * Utility class for validation logic
 */
public class Validation {

    public static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    /**
     * Validates if month is within valid range
     * @param month month value to validate
     * @return true if valid (1-12)
     */
    public static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    /**
     * Validates if day is within valid range
     * @param day day value to validate
     * @return true if valid (1-31)
     */
    public static boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }

    /**
     * Validates if rating is within valid range
     * @param rating rating value to validate
     * @return true if valid (0-100)
     */
    public static boolean isValidRating(float rating) {
        return rating >= 0 && rating <= 5;
    }

    /**
     * Validates if a string is not null or empty
     * @param value string to validate
     * @return true if not null and not empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validates if an ID is valid (positive number)
     * @param id ID to validate
     * @return true if valid (> 0)
     */
    public static boolean isValidId(long id) {
        return id > 0;
    }

    /**
     * Validates if a number is positive
     * @param number number to validate
     * @return true if positive (> 0)
     */
    public static boolean isPositive(int number) {
        return number > 0;
    }

    /**
     * Validates if a number is non-negative
     * @param number number to validate
     * @return true if non-negative (>= 0)
     */
    public static boolean isNonNegative(int number) {
        return number >= 0;
    }
}