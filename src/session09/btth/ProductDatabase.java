package session09.btth;

import java.util.*;

class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void updateProduct(String id, String newName, double newPrice) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(newName);
                p.setPrice(newPrice);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void removeProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getProducts() {
        return products;
    }
}

