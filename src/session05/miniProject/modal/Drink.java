package session05.miniProject.modal;

public class Drink extends MenuItem {

    public enum Size {
        S(0.0),
        M(0.1),
        L(0.2);

        private final double extraRate;

        Size(double extraRate) {
            this.extraRate = extraRate;
        }

        public double getExtraRate() {
            return extraRate;
        }
    }

    private Size size;

    public Drink(String id, String name, double basePrice, int stock, Size size) {
        super(id, name, basePrice, stock);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * (1 + size.getExtraRate());
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s, %.2f, tồn: %d)",
                getId(), getName(), size.name(), calculatePrice(), getStock());
    }
}
