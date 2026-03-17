package session08.homework06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChọn kênh bán hàng:");
            System.out.println("1. Website");
            System.out.println("2. Mobile App");
            System.out.println("3. POS");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();
            SalesChannelFactory factory = null;

            switch (choice) {
                case 1:
                    System.out.println("Bạn đã chọn kênh Website");
                    factory = new WebsiteFactory();
                    break;
                case 2:
                    System.out.println("Bạn đã chọn kênh Mobile App");
                    factory = new MobileAppFactory();
                    break;
                case 3:
                    System.out.println("Bạn đã chọn kênh POS");
                    factory = new POSFactory();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
            }

            OrderService orderService = new OrderService(factory);

            System.out.print("Nhập tên sản phẩm: ");
            String product = sc.next();
            System.out.print("Nhập giá sản phẩm: ");
            double price = sc.nextDouble();
            System.out.print("Nhập số lượng: ");
            int quantity = sc.nextInt();

            orderService.processOrder(product, price, quantity);
        }
    }
}
