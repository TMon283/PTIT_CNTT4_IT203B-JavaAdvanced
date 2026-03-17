package session08.homework06;

interface DiscountStrategy {
    double calculateDiscount(double amount);
}

interface PaymentMethod {
    void pay(double amount);
}

interface NotificationService {
    void notify(String message);
}

