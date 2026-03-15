package session06.bttrenlop.bt3;

import java.util.Random;

public class Luong2 extends Thread {
    private DataShare dataShare;

    public Luong2(DataShare dataShare) {
        this.dataShare = dataShare;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (dataShare) {
                if (dataShare.getControl() == 2) {
                    int n = dataShare.getNumber();
                    if (n % 2 == 0) {
                        System.out.println(n + " là số chẵn");
                    } else {
                        System.out.println(n + " là số lẻ");
                    }
                    dataShare.setControl(1);
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

