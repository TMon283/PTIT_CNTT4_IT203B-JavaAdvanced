package session07.homework06;

public class KioskFactory implements SalesChannelFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return total -> total;
    }

    @Override
    public PaymentMethod createPaymentMethod() {
        return amount -> System.out.println("Thanh toán qua thẻ tại kiosk: " + amount);
    }

    @Override
    public NotificationService createNotificationService() {
        return (msg, recipient) -> System.out.println("Hiển thị thông báo trên màn hình kiosk: " + msg);
    }
}
