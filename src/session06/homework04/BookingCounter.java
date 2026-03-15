package session06.homework04;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean lockOrderAB; // true: khóa A trước, false: khóa B trước
    private int comboSold = 0;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB, boolean lockOrderAB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.lockOrderAB = lockOrderAB;
    }

    @Override
    public void run() {
        while (true) {
            if (roomA.isSoldOut() || roomB.isSoldOut()) {
                System.out.println(counterName + ": Hết vé combo, dừng bán.");
                break;
            }

            boolean success = sellCombo();
            if (success) {
                comboSold++;
            } else {
                System.out.println(counterName + ": Bán combo thất bại.");
                break;
            }

            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public boolean sellCombo() {
        if (lockOrderAB) {
            synchronized (roomA) {
                Ticket ticketA = roomA.sellTicket();
                if (ticketA == null) return false;
                System.out.println(counterName + ": Đã lấy vé " + ticketA.getTicketId());
                synchronized (roomB) {
                    Ticket ticketB = roomB.sellTicket();
                    if (ticketB == null) {
                        ticketA.setSold(false);
                        System.out.println(counterName + ": Không lấy được vé phòng B, trả lại " + ticketA.getTicketId());
                        return false;
                    }
                    System.out.println(counterName + " bán combo thành công: " + ticketA.getTicketId() + " & " + ticketB.getTicketId());
                    return true;
                }
            }
        } else {
            synchronized (roomB) {
                Ticket ticketB = roomB.sellTicket();
                if (ticketB == null) return false;
                System.out.println(counterName + ": Đã lấy vé " + ticketB.getTicketId());
                synchronized (roomA) {
                    Ticket ticketA = roomA.sellTicket();
                    if (ticketA == null) {
                        ticketB.setSold(false);
                        System.out.println(counterName + ": Không lấy được vé phòng A, trả lại " + ticketB.getTicketId());
                        return false;
                    }
                    System.out.println(counterName + " bán combo thành công: " + ticketA.getTicketId() + " & " + ticketB.getTicketId());
                    return true;
                }
            }
        }
    }

    public int getComboSold() { return comboSold; }
}


