package session06.homework06;

public class CinemaRoom {
    private String name;
    private int totalTickets;
    private int soldTickets;

    public CinemaRoom(String name, int totalTickets) {
        this.name = name;
        this.totalTickets = totalTickets;
        this.soldTickets = 0;
    }

    public synchronized boolean sellTicket() {
        if (soldTickets < totalTickets) {
            soldTickets++;
            return true;
        }
        return false;
    }

    public synchronized void addTickets(int amount) {
        totalTickets += amount;
    }

    public String getName() { return name; }
    public int getTotalTickets() { return totalTickets; }
    public int getSoldTickets() { return soldTickets; }
}

