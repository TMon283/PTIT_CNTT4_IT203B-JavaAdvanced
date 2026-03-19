package session09.demo.helper;

import java.util.Scanner;

public class InputHelper {
    public static int inputInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input. Please reenter a valid integer.");
            }
        }
    }

    public static double inputDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input. Please reenter a valid double.");
            }
        }
    }
}