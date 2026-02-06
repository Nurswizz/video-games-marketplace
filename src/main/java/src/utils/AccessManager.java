package src.utils;

public final class AccessManager {
    private AccessManager() {
        // prevent instantiation
    }
    public static void requireAdmin() {
        if (!Session.getInstance().isAdmin()) {
            throw new SecurityException("Admin access required");
        }
    }
}
