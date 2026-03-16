package session07.homework01;

public class EmailService {
    public void sendConfirmation(Order order) {
        System.out.println("Đã gửi email đến " + order.customer.email +
                ": Đơn hàng " + order.orderId + " đã được tạo");
    }
}

