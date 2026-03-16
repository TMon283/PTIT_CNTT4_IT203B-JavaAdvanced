package session07.homework01;

public class OrderRepository {
    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.orderId);
    }
}

