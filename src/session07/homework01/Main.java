package session07.homework01;

public class Main {
    public static void main(String[] args) {
        // Tạo sản phẩm
        Product sp1 = new Product("SP01", "Laptop", 15000000);
        Product sp2 = new Product("SP02", "Chuột", 300000);
        System.out.println("Tạo sản phẩm: SP01 - Laptop - 15000000, SP02 - Chuột - 300000");
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        // Tạo khách hàng
        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");
        System.out.println("Tạo khách hàng: Nguyễn Văn A - a@example.com");
        System.out.println("Đã thêm khách hàng");

        // Tạo đơn hàng
        Order order = new Order("ORD001", customer);
        order.addProduct(sp1, 1);
        order.addProduct(sp2, 2);
        System.out.println("Tạo đơn hàng: SP01 (1 cái), SP02 (2 cái)");
        System.out.println("Đơn hàng ORD001 được tạo");

        // Tính tổng tiền
        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);
        System.out.println("Tính tổng tiền");
        System.out.println("Tổng tiền: " + total);

        // Lưu đơn hàng
        OrderRepository repository = new OrderRepository();
        repository.save(order);

        // Gửi email xác nhận
        EmailService emailService = new EmailService();
        emailService.sendConfirmation(order);
    }
}

