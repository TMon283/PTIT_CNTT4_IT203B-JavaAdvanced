package session04.homework03;

public class UserProcessor {
    public String processEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email không hợp lệ: thiếu ký tự @");
        }
        String[] parts = email.split("@");
        if (parts.length != 2 || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Email không hợp lệ: thiếu tên miền");
        }
        return email.toLowerCase();
    }
}

