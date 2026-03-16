package session07.homework03;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // COD
        System.out.println("Loại thanh toán: COD");
        processor.processCOD(new CODPayment(), 500000);

        // Credit Card
        System.out.println("\nLoại thanh toán: Thẻ tín dụng");
        processor.processCard(new CreditCardPayment(), 1000000);

        // MoMo
        System.out.println("\nLoại thanh toán: Ví MoMo");
        processor.processEWallet(new MomoPayment(), 750000);

        // Kiểm tra LSP
        System.out.println("\nKiểm tra LSP");
        CardPayable payment = new CreditCardPayment();
        processor.processCard(payment, 1000000);

        // Thay thế CreditCardPayment bằng MomoPayment trong cùng interface
        EWalletPayable payment2 = new MomoPayment();
        processor.processEWallet(payment2, 1000000);

        System.out.println("Chương trình vẫn chạy đúng");
    }
}

