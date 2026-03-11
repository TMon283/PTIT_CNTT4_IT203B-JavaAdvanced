package session02.homework05;

public class SuperAdmin implements UserAction, AdminAction {
    @Override
    public void logActivity(String activity) {
        UserAction.super.logActivity(activity);
        AdminAction.super.logActivity(activity);
        System.out.println("SuperAdmin log: " + activity);
    }
}
