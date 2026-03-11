package session03.homework05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public record User(String username, String fullname) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander", "Alexander A."),
                new User("charlotte", "Charlotte C."),
                new User("Benjamin", "Benjamin B."),
                new User("alex", "Alex X."),
                new User("charlotte", "Charlotte Duplicate")
        );

        users.stream()
                .sorted((u1, u2) ->
                        Integer.compare(
                                u2.username().length(),
                                u1.username().length()
                        ))
                .limit(3)
                .forEach(user -> System.out.println(user.username()));
    }
}
