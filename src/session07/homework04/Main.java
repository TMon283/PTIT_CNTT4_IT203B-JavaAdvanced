package session07.homework04;

public class Main {
    public static void main(String[] args) {
        // Cấu hình 1: FileOrderRepository + EmailService
        System.out.println("Dùng FileOrderRepository và EmailService");
        OrderRepository fileRepo = new FileOrderRepository();
        NotificationService emailService = new EmailService();
        OrderService orderService1 = new OrderService(fileRepo, emailService);

        Order order1 = new Order("ORD001");
        orderService1.createOrder(order1);

        // Cấu hình 2: DatabaseOrderRepository + SMSNotification
        System.out.println("\nĐổi sang DatabaseOrderRepository và SMSNotification");
        OrderRepository dbRepo = new DatabaseOrderRepository();
        NotificationService smsService = new SMSNotification();
        OrderService orderService2 = new OrderService(dbRepo, smsService);

        Order order2 = new Order("ORD002");
        orderService2.createOrder(order2);

    }
}
