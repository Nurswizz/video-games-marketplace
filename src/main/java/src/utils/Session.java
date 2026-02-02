package src.utils;

public final class Session {

    private static Session instance;

    private final long userId;
    private final boolean isAdmin;

    private Session(long userId, boolean isAdmin) {
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public static void login(long userId, boolean isAdmin) {
        instance = new Session(userId, isAdmin);
    }

    public static Session getInstance() {
        if (instance == null) {
            throw new IllegalStateException("User is not logged in");
        }
        return instance;
    }

    public static void logout() {
        instance = null;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
