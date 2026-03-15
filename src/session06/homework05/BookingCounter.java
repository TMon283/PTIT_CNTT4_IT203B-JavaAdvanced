package session06.homework05;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int totalSold = 0;
    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        while (true) {
            if (roomA.isSoldOut() && roomB.isSoldOut() && !TicketSupplier.supplierRunning) {
                break;
            }

            TicketPool chosenRoom = random.nextBoolean() ? roomA : roomB;
            Ticket ticket = chosenRoom.sellTicket(counterName);

            if (ticket != null) {
                totalSold++;
                System.out.println(counterName + " bán vé phòng " + chosenRoom.getRoomName());
                System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
            }

            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public int getTotalSold() { return totalSold; }
}

