package session14.miniProject.model;

public class OrderItemRequest {
    private final int productId;
    private final int quantity;

    public OrderItemRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
