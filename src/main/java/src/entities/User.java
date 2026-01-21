package src.entities;

public class User {

    private long id;
    private final String username;
    private final String password;
    private final String email;
    private final boolean isAdmin;
    private float balance;

    public User(String username, String password, String email) {
        this(0, username, password, email, false);
    }

    public User(long id, String username, String password,
                String email, boolean isAdmin) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.balance = 0;
    }

    public long getId() {
        return id;
    }

    // set after insert
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
