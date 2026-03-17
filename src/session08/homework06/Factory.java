package session08.homework06;

class WebsiteDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.10; // giảm 10%
    }
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount);
    }
}

class EmailNotification implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Gửi email: " + message);
    }
}

class WebsiteFactory extends SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new WebsiteDiscount(); }
    public PaymentMethod createPaymentMethod() { return new CreditCardPayment(); }
    public NotificationService createNotificationService() { return new EmailNotification(); }
}

