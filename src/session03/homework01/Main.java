package session03.homework01;

import java.util.Arrays;
import java.util.List;

public class Main {
    public record User(String username, String email, String status) {}

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Alice", "alice@gmail.com", "ACTIVE"),
                new User("Bob", "bob@gmail.com", "INACTIVE"),
                new User("Charlie", "charlie@gmail.com", "ACTIVE")
        );

        users.stream().forEach(System.out::println);
    }
}
