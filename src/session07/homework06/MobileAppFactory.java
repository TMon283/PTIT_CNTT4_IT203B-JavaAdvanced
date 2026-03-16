package session07.homework06;

public class MobileAppFactory implements SalesChannelFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return total -> total * 0.85;
    }

    @Override
    public PaymentMethod createPaymentMethod() {
        return amount -> System.out.println("Xử lý thanh toán MoMo tích hợp: " + amount);
    }

    @Override
    public NotificationService createNotificationService() {
        return (msg, recipient) -> System.out.println("Gửi push notification: " + msg);
    }
}

