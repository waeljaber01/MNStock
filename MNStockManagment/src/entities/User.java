package entities;

public class User {
    private String username;
    private Role role;

    public enum Role {
        ADMIN,
        USER
    }

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}
