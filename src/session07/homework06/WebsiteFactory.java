package session07.homework06;

public class WebsiteFactory implements SalesChannelFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return total -> total * 0.9;
    }

    @Override
    public PaymentMethod createPaymentMethod() {
        return amount -> System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng online: " + amount);
    }

    @Override
    public NotificationService createNotificationService() {
        return (msg, recipient) -> System.out.println("Gửi email xác nhận: " + msg);
    }
}
