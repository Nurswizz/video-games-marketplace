package src.utils;

public class Validation {

    public static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }


    public static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }


    public static boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }


    public static boolean isValidRating(float rating) {
        return rating >= 0 && rating <= 5;
    }


    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidId(long id) {
        return id > 0;
    }


    public static boolean isPositive(int number) {
        return number > 0;
    }


    public static boolean isNonNegative(int number) {
        return number >= 0;
    }
}