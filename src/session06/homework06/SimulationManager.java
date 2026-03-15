package session06.homework06;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationManager {
    private ExecutorService executor;
    private CinemaRoom[] rooms;
    private TicketCounter[] counters;
    private StatisticsManager stats;

    public void startSimulation(Scanner sc) {
        System.out.print("Nhập số phòng: ");
        int numRooms = sc.nextInt();
        System.out.print("Nhập số vé mỗi phòng: ");
        int ticketsPerRoom = sc.nextInt();
        System.out.print("Nhập số quầy: ");
        int numCounters = sc.nextInt();

        rooms = new CinemaRoom[numRooms];
        for (int i = 0; i < numRooms; i++) {
            rooms[i] = new CinemaRoom("Phòng " + (char)('A' + i), ticketsPerRoom);
        }

        counters = new TicketCounter[numCounters];
        executor = Executors.newFixedThreadPool(numCounters);
        for (int i = 0; i < numCounters; i++) {
            counters[i] = new TicketCounter(i + 1, rooms);
            executor.submit(counters[i]);
        }

        stats = new StatisticsManager(rooms);
        System.out.println("Đã khởi tạo hệ thống với " + numRooms + " phòng, " +
                (numRooms * ticketsPerRoom) + " vé, " + numCounters + " quầy");
    }

    public void pauseSimulation() {
        for (TicketCounter counter : counters) counter.pause();
        System.out.println("Đã tạm dừng tất cả quầy bán vé.");
    }

    public void resumeSimulation() {
        for (TicketCounter counter : counters) counter.resume();
        System.out.println("Đã tiếp tục hoạt động.");
    }

    public void addTickets(Scanner sc) {
        System.out.print("Chọn phòng (A, B, ...): ");
        String roomName = sc.next();
        System.out.print("Số vé thêm: ");
        int amount = sc.nextInt();

        for (CinemaRoom room : rooms) {
            if (room.getName().equalsIgnoreCase("Phòng " + roomName)) {
                room.addTickets(amount);
                System.out.println("Đã thêm " + amount + " vé vào " + room.getName());
                return;
            }
        }
        System.out.println("Không tìm thấy phòng.");
    }

    public void showStatistics() {
        stats.showStatistics();
    }

    public void detectDeadlock() {
        new Thread(new DeadlockDetector()).start();
    }

    public void shutdown() {
        if (executor != null) executor.shutdownNow();
        System.out.println("Đang dừng hệ thống...");
    }
}
