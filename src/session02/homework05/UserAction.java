package session02.homework05;

public interface UserAction {
    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }
}
