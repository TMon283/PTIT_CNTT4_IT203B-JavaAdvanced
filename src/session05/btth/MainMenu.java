package session05.btth;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private final Scanner sc = new Scanner(System.in);
    private final ProductService productService = new ProductService();

    public static void main(String[] args) {
        new MainMenu().orderManagement();
    }

    private void orderManagement() {
        int choice;

        do {
            System.out.println("===== PRODUCT MANAGEMENT SYSTEM =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    updateQuantity();
                    break;
                case 4:
                    deleteOutOfStock();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (true);
    }

    private void addProduct() {
        try {
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Tên: ");
            String name = sc.nextLine();
            System.out.print("Số lượng: ");
            int quantity = sc.nextInt();
            System.out.print("Giá: ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.print("Danh mục: ");
            String category = sc.nextLine();
            Product p = new Product(id, name, quantity, price, category);
            productService.addProduct(p);
            System.out.println("Đã thêm sản phẩm.");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private void displayProducts() {
        List<Product> list = productService.findAll();
        if (list.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào.");
            return;
        }
        System.out.printf("%-6s | %-20s | %-10s | %-12s | %-15s%n", "ID", "Tên", "Số lượng", "Giá", "Danh mục");
        list.stream().forEach(p -> System.out.printf("%-6d | %-20s | %-10d | %-12.2f | %-15s%n",
                p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCategory()));
    }

    private void updateQuantity() {
        try {
            System.out.print("ID sản phẩm cần cập nhật: ");
            int id = sc.nextInt();
            System.out.print("Số lượng mới: ");
            int newQty = sc.nextInt();
            productService.updateQuantity(id, newQty);
            System.out.println("Đã cập nhật số lượng.");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private void deleteOutOfStock() {
        productService.deleteOutOfStock();
        System.out.println("Đã xóa các sản phẩm hết hàng (quantity = 0).");
    }
}
