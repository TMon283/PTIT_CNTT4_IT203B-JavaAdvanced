package session06.homework06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimulationManager manager = new SimulationManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== MENU ===");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> manager.startSimulation(sc);
                case 2 -> manager.pauseSimulation();
                case 3 -> manager.resumeSimulation();
                case 4 -> manager.addTickets(sc);
                case 5 -> manager.showStatistics();
                case 6 -> manager.detectDeadlock();
                case 7 -> {
                    manager.shutdown();
                    System.out.println("Kết thúc chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}

