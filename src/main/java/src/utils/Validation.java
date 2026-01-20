package src.utils;

public final class Validation {

    public static boolean validateEmail(String email) {

        if (!email.contains("@")) {
            return false;
        }

        if (!email.contains(".")) {
            return false;
        }

        return true;
    }
}
