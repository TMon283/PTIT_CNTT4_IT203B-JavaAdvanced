package session06.homework06;

public class StatisticsManager {
    private CinemaRoom[] rooms;

    public StatisticsManager(CinemaRoom[] rooms) {
        this.rooms = rooms;
    }

    public void showStatistics() {
        System.out.println("=== THỐNG KÊ HIỆN TẠI ===");
        int totalRevenue = 0;
        for (CinemaRoom room : rooms) {
            System.out.println(room.getName() + ": Đã bán " +
                    room.getSoldTickets() + "/" + room.getTotalTickets() + " vé");
            totalRevenue += room.getSoldTickets() * 75000;
        }
        System.out.println("Tổng doanh thu: " + totalRevenue + " VNĐ");
    }
}

