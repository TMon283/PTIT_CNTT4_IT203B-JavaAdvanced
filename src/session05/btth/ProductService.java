package session05.btth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final List<Product> products = new ArrayList<>();
    public void addProduct(Product product) throws InvalidProductException {
        boolean duplicate = products.stream()
                .anyMatch(p -> p.getId() == product.getId());
        if (duplicate) {
            throw new InvalidProductException("Sản phẩm có ID " + product.getId() + " đã tồn tại.");
        }
        products.add(product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> opt = findById(id);
        if (opt.isEmpty()) {
            throw new InvalidProductException("Không tìm thấy sản phẩm có ID " + id + " để cập nhật.");
        }
        opt.get().setQuantity(newQuantity);
    }

    public void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
    }
}
