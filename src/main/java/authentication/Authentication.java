package authentication;

public class Authentication {
    public boolean authenticate(String username, String password) {
        // Dummy authentication for demonstration
        return "admin".equals(username) && "password".equals(password);
    }
}
