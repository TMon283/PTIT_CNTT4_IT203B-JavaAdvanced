package session05.miniProject.modal;

public class Food extends MenuItem {

    private boolean spicy;

    public Food(String id, String name, double basePrice, int stock, boolean spicy) {
        super(id, name, basePrice, stock);
        this.spicy = spicy;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f, %s, tồn: %d)",
                getId(),
                getName(),
                calculatePrice(),
                spicy ? "cay" : "không cay",
                getStock()
        );
    }
}
