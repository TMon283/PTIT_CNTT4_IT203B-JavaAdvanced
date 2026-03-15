package session06.homework06;

import java.util.concurrent.atomic.AtomicBoolean;

public class TicketCounter implements Runnable {
    private int id;
    private CinemaRoom[] rooms;
    private AtomicBoolean running = new AtomicBoolean(true);
    private AtomicBoolean paused = new AtomicBoolean(false);

    public TicketCounter(int id, CinemaRoom[] rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public void stop() { running.set(false); }
    public void pause() { paused.set(true); }
    public void resume() { paused.set(false); }

    @Override
    public void run() {
        System.out.println("Quầy " + id + " bắt đầu bán vé...");
        while (running.get()) {
            if (paused.get()) {
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                continue;
            }
            for (CinemaRoom room : rooms) {
                if (room.sellTicket()) {
                    System.out.println("Quầy " + id + " bán được 1 vé phòng " + room.getName());
                    try { Thread.sleep(300); } catch (InterruptedException ignored) {}
                }
            }
        }
    }
}

