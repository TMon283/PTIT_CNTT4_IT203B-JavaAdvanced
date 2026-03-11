package session02.homework04;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie")
        );

        users.stream()
                .map(User::getUsername)
                .forEach(System.out::println);

        Supplier<User> userSupplier = User::new;
        User newUser = userSupplier.get();
        System.out.println("New user created: " + newUser.getUsername());
//        (user) -> user.getUsername() => User::getUsername
//        (s) -> System.out.println(s) => System.out::println
//        () -> new User() => User::new
    }
}



