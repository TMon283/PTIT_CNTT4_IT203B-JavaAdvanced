package session07.homework06;

public class OrderService {
    private DiscountStrategy discountStrategy;
    private PaymentMethod paymentMethod;
    private NotificationService notificationService;

    public OrderService(SalesChannelFactory factory) {
        this.discountStrategy = factory.createDiscountStrategy();
        this.paymentMethod = factory.createPaymentMethod();
        this.notificationService = factory.createNotificationService();
    }

    public void processOrder(Order order, double totalAmount) {
        double discounted = discountStrategy.applyDiscount(totalAmount);
        System.out.println("Tổng sau giảm: " + discounted);
        paymentMethod.pay(discounted);
        notificationService.notify("Đơn hàng " + order.getOrderId() + " đã được tạo", order.getCustomer());
    }
}

