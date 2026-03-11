package session02.homework05;

public interface AdminAction {
    default void logActivity(String activity) {
        System.out.println("Admin log: " + activity);
    }
}
