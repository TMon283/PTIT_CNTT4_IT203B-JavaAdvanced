package session07.homework04;

public class OrderService {
    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(Order order) {
        System.out.println("Tạo đơn hàng " + order.getOrderId());
        orderRepository.save(order);
        notificationService.send("Đơn hàng " + order.getOrderId() + " đã được tạo", "recipient");
    }
}

