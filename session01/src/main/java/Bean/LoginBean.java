package Bean;

public class LoginBean {
    public boolean login(String username, String password) {
        return username.equalsIgnoreCase("ty") && password.equalsIgnoreCase("123");
    }
}
