package session02.homework06;

public class Main {
    public static void main(String[] args) {
        UserProcessor processor = UserUtils::convertToUpperCase;

        User user = new User("minh");
        String result = processor.process(user);

        System.out.println(result);
    }
}
