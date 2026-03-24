package session14.miniProject.model;

import java.math.BigDecimal;

public class OrderItem {
    private final int orderDetailId;
    private final int productId;
    private final String productName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderItem(int orderDetailId, int productId, String productName, int quantity, BigDecimal unitPrice) {
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
