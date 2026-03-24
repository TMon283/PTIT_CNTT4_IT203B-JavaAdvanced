package session14.miniProject.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderId;
    private final int userId;
    private final String username;
    private final Timestamp createdAt;
    private final List<OrderItem> items;

    public Order(int orderId, int userId, String username, Timestamp createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }
}
