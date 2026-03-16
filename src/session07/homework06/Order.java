package session07.homework06;

public class Order {
    private String orderId;
    private String customer;

    public Order(String orderId, String customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}

