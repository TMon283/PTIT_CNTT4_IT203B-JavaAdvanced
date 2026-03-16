package session07.homework03;

public class PaymentProcessor {
    public void processCOD(CODPayable codPayment, double amount) {
        codPayment.processCOD(amount);
    }

    public void processCard(CardPayable cardPayment, double amount) {
        cardPayment.processCreditCard(amount);
    }

    public void processEWallet(EWalletPayable eWalletPayment, double amount) {
        eWalletPayment.processMomo(amount);
    }
}

