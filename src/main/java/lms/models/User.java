package lms.models;

public class User {

    private String username;
    private String password;
    private String theme;

    public User(String username, String password, String theme) {
        this.username = username;
        this.password = password;
        this.theme = theme;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTheme() {
        return theme;
    }
}