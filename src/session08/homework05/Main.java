package session08.homework05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        TemperatureSensor sensor = new TemperatureSensor();

        sensor.attach(fan);
        sensor.attach(ac);

        SleepModeCommand sleepMode = new SleepModeCommand(light, ac, fan);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Kích hoạt chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ (giả lập)");
            System.out.println("3. Xem trạng thái thiết bị");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sleepMode.execute();
                    break;
                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = sc.nextInt();
                    sensor.setTemperature(temp);
                    break;
                case 3:
                    System.out.println(fan.getStatus());
                    System.out.println(ac.getStatus());
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}

