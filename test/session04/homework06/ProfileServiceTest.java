package session04.homework06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.*;

class ProfileServiceTest {

    User existingUser;
    List<User> allUsers;

    @BeforeEach
    void setUp() {
        existingUser = new User("old@example.com", "Old Name", LocalDate.of(1990, 1, 1));
        allUsers = new ArrayList<>();
        allUsers.add(existingUser);
        allUsers.add(new User("other@example.com", "Other User", LocalDate.of(1985, 5, 5)));
    }

    @AfterEach
    void tearDown() {
        existingUser = null;
        allUsers = null;
    }

    @Test
    void testValidUpdate() {
        UserProfile newProfile = new UserProfile("new@example.com", "New Name", LocalDate.of(1995, 3, 3));
        User updatedUser = ProfileService.updateProfile(existingUser, newProfile, allUsers);
        assertNotNull(updatedUser);
        assertEquals("new@example.com", updatedUser.email());
    }

    @Test
    void testFutureBirthDate() {
        UserProfile newProfile = new UserProfile("new@example.com", "New Name", LocalDate.now().plusDays(1));
        User result = ProfileService.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }

    @Test
    void testDuplicateEmail() {
        UserProfile newProfile = new UserProfile("other@example.com", "New Name", LocalDate.of(1995, 3, 3));
        User result = ProfileService.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }

    @Test
    void testSameEmailAsCurrent() {
        UserProfile newProfile = new UserProfile("old@example.com", "Updated Name", LocalDate.of(1992, 2, 2));
        User updatedUser = ProfileService.updateProfile(existingUser, newProfile, allUsers);
        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.name());
    }

    @Test
    void testValidUpdateWithEmptyUserList() {
        List<User> emptyList = new ArrayList<>();
        UserProfile newProfile = new UserProfile("unique@example.com", "Unique Name", LocalDate.of(1993, 4, 4));
        User updatedUser = ProfileService.updateProfile(existingUser, newProfile, emptyList);
        assertNotNull(updatedUser);
        assertEquals("unique@example.com", updatedUser.email());
    }

    @Test
    void testDuplicateEmailAndFutureBirthDate() {
        UserProfile newProfile = new UserProfile("other@example.com", "New Name", LocalDate.now().plusDays(1));
        User result = ProfileService.updateProfile(existingUser, newProfile, allUsers);
        assertNull(result);
    }
}
