package src.entities;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private boolean isAdmin = false;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public String  getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
