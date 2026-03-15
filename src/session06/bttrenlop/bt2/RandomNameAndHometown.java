package session06.bttrenlop.bt2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomNameAndHometown extends Thread {
    @Override
    public void run() {
        List<String> students = Arrays.asList("A", "B", "C", "D", "E", "F");
        List<String> hometown = Arrays.asList("Hà Nội", "Ninh Bình", "Phú Thọ", "Thanh Hóa", "Quảng Bình", "Lạng Sơn");
        Random random = new Random();
        while (true) {
            int index = random.nextInt(students.size());
            System.out.println("Bạn tên: " + students.get(index) + " Quê quán: " + hometown.get(index));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
