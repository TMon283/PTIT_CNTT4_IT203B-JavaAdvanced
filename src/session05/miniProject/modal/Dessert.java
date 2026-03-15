package session05.miniProject.modal;

public class Dessert extends MenuItem {

    private boolean cold;

    public Dessert(String id, String name, double basePrice, int stock, boolean cold) {
        super(id, name, basePrice, stock);
        this.cold = cold;
    }

    public boolean isCold() {
        return cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * 0.95;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f, %s, tồn: %d)",
                getId(),
                getName(),
                calculatePrice(),
                cold ? "lạnh" : "không lạnh",
                getStock()
                );
    }
}
