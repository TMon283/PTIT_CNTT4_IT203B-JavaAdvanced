package session04.homework04;

import static org.junit.jupiter.api.Assertions.*;
import static session04.homework04.PasswordStrengthEvaluator.evaluatePasswordStrength;
import org.junit.jupiter.api.Test;

class PasswordStrengthEvaluatorTest {

    @Test
    void testEvaluatePasswordStrength() {
        assertAll(
                () -> assertEquals("Mạnh", evaluatePasswordStrength("Abc123!@")),
                () -> assertEquals("Trung bình", evaluatePasswordStrength("abc123!@")),
                () -> assertEquals("Trung bình", evaluatePasswordStrength("ABC123!@")),
                () -> assertEquals("Trung bình", evaluatePasswordStrength("Abcdef!@")),
                () -> assertEquals("Trung bình", evaluatePasswordStrength("Abc12345")),
                () -> assertEquals("Yếu", evaluatePasswordStrength("Ab1!")),
                () -> assertEquals("Yếu", evaluatePasswordStrength("password")),
                () -> assertEquals("Yếu", evaluatePasswordStrength("ABC12345"))
        );
    }
}
