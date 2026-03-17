package session08.homework06;

class FirstTimeDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.15; // giảm 15%
    }
}

class MomoPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount);
    }
}

class PushNotification implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Gửi push notification: " + message);
    }
}

class MobileAppFactory extends SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new FirstTimeDiscount(); }
    public PaymentMethod createPaymentMethod() { return new MomoPayment(); }
    public NotificationService createNotificationService() { return new PushNotification(); }
}
