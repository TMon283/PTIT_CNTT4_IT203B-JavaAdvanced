package session05.miniProject.repository;

import session05.miniProject.modal.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).orderId().equalsIgnoreCase(order.orderId())) {
                orders.set(i, order);
                return;
            }
        }
        orders.add(order);
    }

    public Optional<Order> findById(String orderId) {
        return orders.stream()
                .filter(o -> o.orderId().equalsIgnoreCase(orderId))
                .findFirst();
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(orders);
    }
}
