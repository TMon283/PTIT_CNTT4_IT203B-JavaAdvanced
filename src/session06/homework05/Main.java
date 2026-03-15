package session06.homework05;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy thường 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy thường 2", roomA, roomB);

        BookingCounterCombo comboCounter1 = new BookingCounterCombo("Quầy combo 1", roomA, roomB, true);
        BookingCounterCombo comboCounter2 = new BookingCounterCombo("Quầy combo 2", roomA, roomB, true);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 5, 5000, 2);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
        Thread tCombo1 = new Thread(comboCounter1);
        Thread tCombo2 = new Thread(comboCounter2);
        Thread tSupplier = new Thread(supplier);

        t1.start();
        t2.start();
        tCombo1.start();
        tCombo2.start();
        tSupplier.start();

        try {
            t1.join();
            t2.join();
            tCombo1.join();
            tCombo2.join();
            tSupplier.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Kết thúc chương trình ---");
        System.out.println("Quầy thường 1 bán được: " + counter1.getTotalSold() + " vé");
        System.out.println("Quầy thường 2 bán được: " + counter2.getTotalSold() + " vé");
        System.out.println("Quầy combo 1 bán được: " + comboCounter1.getComboSold() + " combo");
        System.out.println("Quầy combo 2 bán được: " + comboCounter2.getComboSold() + " combo");
        System.out.println("Vé còn lại phòng A: " + roomA.getAvailableCount());
        System.out.println("Vé còn lại phòng B: " + roomB.getAvailableCount());
    }
}


