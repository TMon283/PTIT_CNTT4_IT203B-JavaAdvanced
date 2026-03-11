package session03.homework03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public static record User(String username) {}

    private final List<User> users = Arrays.asList(
            new User("alice"),
            new User("bob"),
            new User("claire")
    );

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.username().equals(username))
                .findFirst();
    }
}

