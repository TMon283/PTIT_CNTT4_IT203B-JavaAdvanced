package session06.bttrenlop.bt1;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RandomName randomName = new RandomName();
        Thread t1 = new Thread(randomName);
        t1.start();
    }
}

