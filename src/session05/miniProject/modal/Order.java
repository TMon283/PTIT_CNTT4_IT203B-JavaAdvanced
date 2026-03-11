package session05.miniProject.modal;

import java.util.ArrayList;
import java.util.List;

public record Order(String orderId, List<OrderItem> items, Status status) {

    public enum Status {
        PENDING,
        PAID,
        CANCELLED
    }

    public Order(String orderId) {
        this(orderId, new ArrayList<>(), Status.PENDING);
    }

    public double totalPrice() {
        return items.stream()
                .mapToDouble(OrderItem::subtotal)
                .sum();
    }
}
