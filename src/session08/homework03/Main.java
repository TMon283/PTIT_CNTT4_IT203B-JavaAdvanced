package session08.homework03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RemoteControl remote = new RemoteControl();

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Chọn nút số:");
                    int slot = sc.nextInt();
                    System.out.println("Chọn lệnh: 1. Bật đèn, 2. Tắt đèn, 3. Bật quạt, 4. Tắt quạt, 5. Set điều hòa");
                    int cmd = sc.nextInt();
                    Command command = null;
                    switch (cmd) {
                        case 1: command = new LightOnCommand(light); break;
                        case 2: command = new LightOffCommand(light); break;
                        case 3: command = new FanOnCommand(fan); break;
                        case 4: command = new FanOffCommand(fan); break;
                        case 5:
                            System.out.print("Nhập nhiệt độ: ");
                            int temp = sc.nextInt();
                            command = new ACSetTemperatureCommand(ac, temp);
                            break;
                    }
                    if (command != null) remote.setCommand(slot, command);
                    break;
                case 2:
                    System.out.print("Nhấn nút số: ");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;
                case 3:
                    remote.pressUndo();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}

