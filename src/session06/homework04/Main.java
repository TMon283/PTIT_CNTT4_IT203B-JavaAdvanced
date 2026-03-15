package session06.homework04;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 5);
        TicketPool roomB = new TicketPool("B", 5);

        // Deadlock: quầy 1 khóa A trước, quầy 2 khóa B trước
        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB, true);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB, false);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();
    }
}

