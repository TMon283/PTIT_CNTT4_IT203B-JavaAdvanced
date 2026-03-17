package session08.homework06;

abstract class SalesChannelFactory {
    public abstract DiscountStrategy createDiscountStrategy();
    public abstract PaymentMethod createPaymentMethod();
    public abstract NotificationService createNotificationService();
}

