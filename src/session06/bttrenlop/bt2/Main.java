package session06.bttrenlop.bt2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RandomNameAndHometown rand = new RandomNameAndHometown();
        Thread t1 = new Thread(rand);
        t1.start();
    }
}