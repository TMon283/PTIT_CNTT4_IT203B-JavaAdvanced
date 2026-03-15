package session06.homework05;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;
    private int ticketCounter;

    public TicketPool(String roomName, int capacity) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
        ticketCounter = capacity;
    }

    public synchronized Ticket sellTicket(String counterName) {
        while (getAvailableCount() == 0 && TicketSupplier.supplierRunning) {
            try {
                System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public synchronized void addTickets(int count) {
        for (int i = 1; i <= count; i++) {
            ticketCounter++;
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", ticketCounter), roomName));
        }
        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        notifyAll();
    }

    public synchronized int getAvailableCount() {
        return (int) tickets.stream().filter(t -> !t.isSold()).count();
    }

    public boolean isSoldOut() {
        return tickets.stream().allMatch(Ticket::isSold);
    }

    public String getRoomName() { return roomName; }
}

