package session01.homework03;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}

