package session05.miniProject.modal;

import java.util.Objects;

public abstract class MenuItem {

    private String id;
    private String name;
    private double basePrice;
    private int stock;

    protected MenuItem(String id, String name, double basePrice, int stock) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract double calculatePrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f, tồn: %d)", id, name, calculatePrice(), stock);
    }
}
