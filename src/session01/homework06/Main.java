package session01.homework06;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User();

        try {
            user.setAge(-5);
        } catch (InvalidAgeException e) {
            Logger.error(e.getMessage(), e);
        }

        try {
            UserFileService.processUserData("Thông tin người dùng");
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        }

        user.printName();
    }
}

