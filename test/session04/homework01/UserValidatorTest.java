package session04.homework01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    UserValidator validator = new UserValidator();

    @Test
    void TC01_validUsername() {
        assertTrue(validator.isValidUsername("user123"), "Username hợp lệ");
    }

    @Test
    void TC02_tooShortUsername() {
        assertFalse(validator.isValidUsername("abc"), "Username quá ngắn");
    }

    @Test
    void TC03_containsWhitespace() {
        assertFalse(validator.isValidUsername("user name"), "Username chứa khoảng trắng");
    }
}
