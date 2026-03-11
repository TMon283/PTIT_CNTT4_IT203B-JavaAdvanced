package session04.homework02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService service = new UserService();

    @Test
    void testAge18_validBoundary() {
        int age = 18;
        boolean result = service.checkRegistrationAge(age);
        assertEquals(true, result);
    }

    @Test
    void testAge17_invalidTooYoung() {
        int age = 17;
        boolean result = service.checkRegistrationAge(age);
        assertEquals(false, result);
    }

    @Test
    void testAgeNegative_throwException() {
        int age = -1;
        assertThrows(IllegalArgumentException.class, () -> service.checkRegistrationAge(age));
    }
}
