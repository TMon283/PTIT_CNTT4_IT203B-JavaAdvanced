package session07.homework03;

public class MomoPayment implements EWalletPayable {
    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }
}

