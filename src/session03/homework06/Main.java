package session03.homework06;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static record Post(String title, List<String> tags) {}

    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post("Java intro", List.of("java", "backend")),
                new Post("Python data", List.of("python", "data")),
                new Post("Advanced Java", List.of("java", "performance"))
        );

        List<String> allTags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .collect(Collectors.toList());

        List<String> uniqueTags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("All tags: " + allTags);
        System.out.println("Unique tags: " + uniqueTags);
    }
}
