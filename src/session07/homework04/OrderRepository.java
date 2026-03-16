package session07.homework04;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

