package session07.homework06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chọn kênh bán: 1. Website, 2. Mobile App, 3. POS, 4. Kiosk");
        int choice = sc.nextInt();

        SalesChannelFactory factory;
        switch (choice) {
            case 1: factory = new WebsiteFactory(); break;
            case 2: factory = new MobileAppFactory(); break;
            case 3: factory = new StorePOSFactory(); break;
            case 4: factory = new KioskFactory(); break;
            default: throw new IllegalArgumentException("Kênh không hợp lệ");
        }

        OrderService orderService = new OrderService(factory);
        Order order = new Order("ORD001", "Nguyễn Văn A");
        orderService.processOrder(order, 1000000);
    }
}

