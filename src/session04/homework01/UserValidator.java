package session04.homework01;

public class UserValidator {
    public boolean isValidUsername(String username) {
        return username != null
                && username.length() >= 6
                && username.length() <= 20
                && !username.contains(" ");
    }
}
