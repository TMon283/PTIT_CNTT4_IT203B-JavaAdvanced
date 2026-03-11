package session01.homework05;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-10);
            System.out.println("Tuổi người dùng: " + user.getAge());
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

