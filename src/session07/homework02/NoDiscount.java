package session07.homework02;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}

