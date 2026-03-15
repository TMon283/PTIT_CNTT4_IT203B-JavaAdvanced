package session05.miniProject.modal;

public record OrderItem(MenuItem item, int quantity) {
    public double subtotal() {
        return item.calculatePrice() * quantity;
    }
}
