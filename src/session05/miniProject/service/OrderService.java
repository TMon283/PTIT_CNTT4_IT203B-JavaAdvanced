package session05.miniProject.service;

import session05.miniProject.exception.InsufficientStockException;
import session05.miniProject.exception.InvalidOrderIdException;
import session05.miniProject.modal.MenuItem;
import session05.miniProject.modal.Order;
import session05.miniProject.modal.OrderItem;
import session05.miniProject.repository.MenuRepository;
import session05.miniProject.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    private final Map<String, Double> discounts = new HashMap<>();
    private final Map<String, Double> serviceFees = new HashMap<>();

    public OrderService(MenuRepository menuRepository, OrderRepository orderRepository) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String orderId) {
        Order order = new Order(orderId);
        orderRepository.save(order);
        return order;
    }

    public void addItemToOrder(String orderId, String menuItemId, int quantity)
            throws InvalidOrderIdException, InsufficientStockException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new InvalidOrderIdException("Không tìm thấy đơn hàng: " + orderId));

        MenuItem menuItem = menuRepository.findById(menuItemId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy món: " + menuItemId));

        if (menuItem.getStock() < quantity) {
            throw new InsufficientStockException("Không đủ số lượng tồn kho cho món " + menuItem.getName());
        }

        menuItem.setStock(menuItem.getStock() - quantity);

        List<OrderItem> items = order.items();
        for (int i = 0; i < items.size(); i++) {
            OrderItem existing = items.get(i);
            if (existing.item().getId().equalsIgnoreCase(menuItemId)) {
                int newQuantity = existing.quantity() + quantity;
                items.set(i, new OrderItem(menuItem, newQuantity));
                return;
            }
        }
        items.add(new OrderItem(menuItem, quantity));
    }

    public double calculateTotal(String orderId) throws InvalidOrderIdException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new InvalidOrderIdException("Không tìm thấy đơn hàng: " + orderId));
        double baseTotal = order.totalPrice();
        double discount = discounts.getOrDefault(orderId, 0.0);
        double serviceFee = serviceFees.getOrDefault(orderId, 0.0);
        return baseTotal * (1 - discount / 100.0) + serviceFee;
    }

    public void applyDiscount(String orderId, double discountPercent, double serviceFee)
            throws InvalidOrderIdException {
        if (!orderRepository.findById(orderId).isPresent()) {
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng: " + orderId);
        }
        discounts.put(orderId, discountPercent);
        serviceFees.put(orderId, serviceFee);
    }

    public void updateStatus(String orderId, Order.Status status) throws InvalidOrderIdException {
        Order current = orderRepository.findById(orderId)
                .orElseThrow(() -> new InvalidOrderIdException("Không tìm thấy đơn hàng: " + orderId));
        Order updated = new Order(current.orderId(), current.items(), status);
        orderRepository.save(updated);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
