package session06.bttrenlop.bt1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomName extends Thread {
    @Override
    public void run() {
        List<String> students = Arrays.asList("A", "B", "C", "D", "E", "F");

        Random random = new Random();
        while (true) {
            String name = students.get(random.nextInt(students.size()));
            System.out.println("Bạn tên: " + name);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
