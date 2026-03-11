package session03.homework02;

import java.util.List;

public class Main {
    public record User(String username, String email) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Alice", "alice@gmail.com"),
                new User("Bob", "bob@yahoo.com"),
                new User("Charlie", "charlie@gmail.com")
        );

        users.stream()
                .filter(user -> user.email.endsWith("@gmail.com"))
                .forEach(System.out::println);
    }
}
