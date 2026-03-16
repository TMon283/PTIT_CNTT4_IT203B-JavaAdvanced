package session07.homework06;

public interface SalesChannelFactory {
    DiscountStrategy createDiscountStrategy();
    PaymentMethod createPaymentMethod();
    NotificationService createNotificationService();
}

