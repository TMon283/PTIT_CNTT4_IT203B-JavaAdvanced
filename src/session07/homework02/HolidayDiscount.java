package session07.homework02;

public class HolidayDiscount implements DiscountStrategy {
    private double percent = 15;

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - totalAmount * percent / 100;
    }
}
