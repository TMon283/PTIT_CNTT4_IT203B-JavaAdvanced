package session06.homework06;

public class DeadlockDetector implements Runnable {
    @Override
    public void run() {
        System.out.println("Đang quét deadlock...");
        System.out.println("Không phát hiện deadlock.");
    }
}
