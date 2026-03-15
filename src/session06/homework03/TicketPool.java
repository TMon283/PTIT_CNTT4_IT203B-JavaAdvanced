package session06.homework03;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;
    private int ticketCounter;

    public TicketPool(String roomName, int numberOfTickets) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        for (int i = 1; i <= numberOfTickets; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
        ticketCounter = numberOfTickets;
    }

    public synchronized Ticket sellTicket(String counterName) {
        while (remainingTickets() == 0 && TicketSupplier.supplierRunning) {
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

    public boolean isSoldOut() {
        return tickets.stream().allMatch(Ticket::isSold);
    }

    public int remainingTickets() {
        return (int) tickets.stream().filter(t -> !t.isSold()).count();
    }

    public String getRoomName() {
        return roomName;
    }
}
