package session07.homework06;

public class StorePOSFactory implements SalesChannelFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return total -> total * 0.95;
    }

    @Override
    public PaymentMethod createPaymentMethod() {
        return amount -> System.out.println("Thanh toán tiền mặt tại quầy: " + amount);
    }

    @Override
    public NotificationService createNotificationService() {
        return (msg, recipient) -> System.out.println("In hóa đơn giấy: " + msg);
    }
}

