package session06.bttrenlop.bt3;

public class Main {
    public static void main(String[] args) {
        DataShare dataShare = new DataShare();
        dataShare.setControl(1);

        Luong1 luong1 = new Luong1(dataShare);
        Luong2 luong2 = new Luong2(dataShare);

        luong1.start();
        luong2.start();
    }
}

