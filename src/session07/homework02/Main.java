package session07.homework02;

public class Main {
    public static void main(String[] args) {
        double totalAmount = 1000000;

        // PercentageDiscount 10%
        OrderCalculator calc1 = new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        System.out.println("Số tiền sau giảm: " + calc1.calculate(totalAmount));

        // FixedDiscount 50.000
        OrderCalculator calc2 = new OrderCalculator(new FixedDiscount(50000));
        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        System.out.println("Số tiền sau giảm: " + calc2.calculate(totalAmount));

        // NoDiscount
        OrderCalculator calc3 = new OrderCalculator(new NoDiscount());
        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        System.out.println("Số tiền sau giảm: " + calc3.calculate(totalAmount));

        // HolidayDiscount 15%
        OrderCalculator calc4 = new OrderCalculator(new HolidayDiscount());
        System.out.println("\nThêm HolidayDiscount 15% (không sửa code cũ)");
        System.out.println("Số tiền sau giảm: " + calc4.calculate(totalAmount));
    }
}

