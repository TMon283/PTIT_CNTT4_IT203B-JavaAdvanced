package session06.bttrenlop.bt3;

import java.util.Random;

public class Luong1 extends Thread {
    private DataShare dataShare;

    public Luong1(DataShare dataShare) {
        this.dataShare = dataShare;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            synchronized (dataShare) {
                if (dataShare.getControl() == 1) {
                    int number = random.nextInt(100);
                    dataShare.setNumber(number);
                    System.out.println("Thread 1 Sinh số: "+ dataShare.getNumber());
                    dataShare.setControl(2);
                    dataShare.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        dataShare.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }
}
