package session04.homework06;

import java.time.LocalDate;
import java.util.List;

public class ProfileService {

    public static User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        if (newProfile.birthDate().isAfter(LocalDate.now())) {
            return null;
        }

        for (User u : allUsers) {
            if (!u.equals(existingUser) && u.email().equalsIgnoreCase(newProfile.email())) {
                return null;
            }
        }

        return new User(newProfile.email(), newProfile.name(), newProfile.birthDate());
    }
}


