package src.utils;

public class Session {

    private static long id;

    public static void login(long userId) {
        id =  userId;
    }

    public static long getId() {
        return id;
    }
}
