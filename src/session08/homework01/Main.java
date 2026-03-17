package session08.homework01;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();
        HardwareConnection connection = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    connection = HardwareConnection.getInstance();
                    break;
                case 2:
                    System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa");
                    int type = sc.nextInt();
                    DeviceFactory factory = null;
                    if (type == 1) factory = new LightFactory();
                    else if (type == 2) factory = new FanFactory();
                    else if (type == 3) factory = new AirConditionerFactory();
                    if (factory != null) {
                        devices.add(factory.createDevice());
                    }
                    break;
                case 3:
                    System.out.println("Chọn thiết bị vừa tạo (index bắt đầu từ 1): ");
                    int idxOn = sc.nextInt();
                    if (idxOn > 0 && idxOn <= devices.size()) {
                        devices.get(idxOn - 1).turnOn();
                    }
                    break;
                case 4:
                    System.out.println("Chọn thiết bị vừa tạo (index bắt đầu từ 1): ");
                    int idxOff = sc.nextInt();
                    if (idxOff > 0 && idxOff <= devices.size()) {
                        devices.get(idxOff - 1).turnOff();
                    }
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}

