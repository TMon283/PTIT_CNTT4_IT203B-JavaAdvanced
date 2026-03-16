package session07.homework01;

import java.util.Map;

public class OrderCalculator {
    public double calculateTotal(Order order) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : order.products.entrySet()) {
            sum += entry.getKey().price * entry.getValue();
        }
        order.total = sum;
        return sum;
    }
}

