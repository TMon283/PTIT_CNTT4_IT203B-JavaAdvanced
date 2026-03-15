package session06.homework06;

public class Ticket {
    private String type;
    private double price;
    private CinemaRoom room;

    public Ticket(String type, double price, CinemaRoom room) {
        this.type = type;
        this.price = price;
        this.room = room;
    }

    public double getPrice() { return price; }
    public CinemaRoom getRoom() { return room; }
}

