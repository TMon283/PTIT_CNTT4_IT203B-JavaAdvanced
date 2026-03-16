package session07.homework01;

import java.util.*;

public class Order {
    String orderId;
    Customer customer;
    Map<Product, Integer> products = new HashMap<>();
    double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }
}

