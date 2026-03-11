package session02.homework03;

@FunctionalInterface
public interface Authenticatable {
    String getPassword();

    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "***" + rawPassword + "***";
    }
}

