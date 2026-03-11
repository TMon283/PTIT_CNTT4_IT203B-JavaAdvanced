package session04.homework05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class PermissionEvaluatorTest {

    User admin;
    User moderator;
    User user;

    @BeforeEach
    void setUp() {
        admin = new User(Role.ADMIN);
        moderator = new User(Role.MODERATOR);
        user = new User(Role.USER);
    }

    @AfterEach
    void tearDown() {
        admin = null;
        moderator = null;
        user = null;
    }

    @Test
    void testAdminPermissions() {
        assertAll(
                () -> assertTrue(PermissionEvaluator.canPerformAction(admin, Action.DELETE_USER)),
                () -> assertTrue(PermissionEvaluator.canPerformAction(admin, Action.LOCK_USER)),
                () -> assertTrue(PermissionEvaluator.canPerformAction(admin, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testModeratorPermissions() {
        assertAll(
                () -> assertFalse(PermissionEvaluator.canPerformAction(moderator, Action.DELETE_USER)),
                () -> assertTrue(PermissionEvaluator.canPerformAction(moderator, Action.LOCK_USER)),
                () -> assertTrue(PermissionEvaluator.canPerformAction(moderator, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testUserPermissions() {
        assertAll(
                () -> assertFalse(PermissionEvaluator.canPerformAction(user, Action.DELETE_USER)),
                () -> assertFalse(PermissionEvaluator.canPerformAction(user, Action.LOCK_USER)),
                () -> assertTrue(PermissionEvaluator.canPerformAction(user, Action.VIEW_PROFILE))
        );
    }
}
