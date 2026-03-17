package session08.homework06;

class MemberDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.05; // giảm 5%
    }
}

class CODPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán COD: " + amount);
    }
}

class PrintReceipt implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("In hóa đơn: " + message);
    }
}

class POSFactory extends SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new MemberDiscount(); }
    public PaymentMethod createPaymentMethod() { return new CODPayment(); }
    public NotificationService createNotificationService() { return new PrintReceipt(); }
}
