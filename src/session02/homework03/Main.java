package session02.homework03;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("123456");
        User u2 = new User("");

        System.out.println(u1.isAuthenticated());
        System.out.println(u2.isAuthenticated());

        System.out.println(Authenticatable.encrypt("mypassword"));
    }
}

