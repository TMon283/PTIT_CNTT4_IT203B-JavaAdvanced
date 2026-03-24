package session14.miniProject;

import session14.miniProject.config.DatabaseConnectionManager;
import session14.miniProject.DAO.OrderDAO;
import session14.miniProject.DAO.ProductDAO;
import session14.miniProject.DAO.ReportingDAO;
import session14.miniProject.DAO.UserDAO;
import session14.miniProject.model.Order;
import session14.miniProject.model.OrderItem;
import session14.miniProject.model.OrderItemRequest;
import session14.miniProject.model.Product;
import session14.miniProject.model.User;
import session14.miniProject.service.FlashSaleService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        UserDAO userDAO = new UserDAO(connectionManager);
        ProductDAO productDAO = new ProductDAO(connectionManager);
        ReportingDAO reportingDAO = new ReportingDAO(connectionManager);
        FlashSaleService flashSaleService = new FlashSaleService(connectionManager, new OrderDAO());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMainMenu();
            System.out.print("Chon nhom chuc nang: ");
            String input = scanner.nextLine().trim();

            try {
                switch (input) {
                    case "1":
                        handleUserManager(scanner, userDAO);
                        break;
                    case "2":
                        handleProductManager(scanner, productDAO, flashSaleService);
                        break;
                    case "3":
                        handleReporting(scanner, reportingDAO);
                        break;
                    case "0":
                        running = false;
                        System.out.println("Da thoat chuong trinh.");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le.");
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== FLASH SALE MENU ===");
        System.out.println("1. User Manager");
        System.out.println("2. Product Manager");
        System.out.println("3. Reporting");
        System.out.println("0. Thoat");
    }

    private static void handleUserManager(Scanner scanner, UserDAO userDAO) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- USER MANAGER ---");
            System.out.println("1. Xem danh sach user");
            System.out.println("2. Them user");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showUsers(userDAO);
                    break;
                case "2":
                    createUser(scanner, userDAO);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void handleProductManager(
        Scanner scanner,
        ProductDAO productDAO,
        FlashSaleService flashSaleService
    ) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- PRODUCT MANAGER ---");
            System.out.println("1. Xem danh sach san pham");
            System.out.println("2. Them san pham");
            System.out.println("3. Sua san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Dat hang");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showProducts(productDAO);
                    break;
                case "2":
                    createProduct(scanner, productDAO);
                    break;
                case "3":
                    updateProduct(scanner, productDAO);
                    break;
                case "4":
                    deleteProduct(scanner, productDAO);
                    break;
                case "5":
                    placeOrder(scanner, flashSaleService);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void handleReporting(Scanner scanner, ReportingDAO reportingDAO) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- REPORTING ---");
            System.out.println("1. Bao cao top 5 nguoi mua");
            System.out.println("2. Bao cao doanh thu theo danh muc (Procedure)");
            System.out.println("3. Tinh doanh thu 1 danh muc (Function)");
            System.out.println("4. Xem danh sach don hang va chi tiet");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showTopBuyers(reportingDAO);
                    break;
                case "2":
                    showRevenueByCategory(reportingDAO);
                    break;
                case "3":
                    showOneCategoryRevenue(scanner, reportingDAO);
                    break;
                case "4":
                    showOrdersWithItems(reportingDAO);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void showUsers(UserDAO userDAO) {
        List<User> users = userDAO.findAllUsers();
        if (users.isEmpty()) {
            System.out.println("Khong co user nao.");
            return;
        }
        users.forEach(u -> System.out.println(u.getId() + " - " + u.getUsername()));
    }

    private static void createUser(Scanner scanner, UserDAO userDAO) {
        System.out.print("Nhap username: ");
        String username = scanner.nextLine().trim();
        userDAO.insertUser(username);
        System.out.println("Them user thanh cong.");
    }

    private static void showProducts(ProductDAO productDAO) {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Khong co san pham nao.");
            return;
        }
        products.forEach(
            p -> System.out.println(
                p.getId() + " | " + p.getName() + " | " + p.getCategory() + " | " + p.getPrice() + " | stock=" + p.getStock()
            )
        );
    }

    private static void createProduct(Scanner scanner, ProductDAO productDAO) {
        System.out.print("Nhap ten san pham: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap danh muc: ");
        String category = scanner.nextLine().trim();
        System.out.print("Nhap gia: ");
        BigDecimal price = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Nhap ton kho: ");
        int stock = Integer.parseInt(scanner.nextLine().trim());
        productDAO.createProduct(name, category, price, stock);
        System.out.println("Them san pham thanh cong.");
    }

    private static void updateProduct(Scanner scanner, ProductDAO productDAO) {
        System.out.print("Nhap product_id can sua: ");
        int productId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhap ten moi: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap danh muc moi: ");
        String category = scanner.nextLine().trim();
        System.out.print("Nhap gia moi: ");
        BigDecimal price = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Nhap ton kho moi: ");
        int stock = Integer.parseInt(scanner.nextLine().trim());
        productDAO.updateProduct(productId, name, category, price, stock);
        System.out.println("Cap nhat san pham thanh cong.");
    }

    private static void deleteProduct(Scanner scanner, ProductDAO productDAO) {
        System.out.print("Nhap product_id can xoa: ");
        int productId = Integer.parseInt(scanner.nextLine().trim());
        productDAO.deleteProduct(productId);
        System.out.println("Xoa san pham thanh cong.");
    }

    private static void placeOrder(Scanner scanner, FlashSaleService flashSaleService) {
        System.out.print("Nhap user_id dat hang: ");
        int userId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhap so luong mat hang trong don: ");
        int totalItems = Integer.parseInt(scanner.nextLine().trim());

        List<OrderItemRequest> items = new ArrayList<>();
        for (int i = 1; i <= totalItems; i++) {
            System.out.println("Mat hang thu " + i + ":");
            System.out.print("- product_id: ");
            int productId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("- so luong: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            items.add(new OrderItemRequest(productId, quantity));
        }

        flashSaleService.placeOrder(userId, items);
    }

    private static void showTopBuyers(ReportingDAO reportingDAO) {
        List<String> topBuyers = reportingDAO.getTop5Buyers();
        if (topBuyers.isEmpty()) {
            System.out.println("Chua co du lieu mua hang.");
            return;
        }
        topBuyers.forEach(System.out::println);
    }

    private static void showRevenueByCategory(ReportingDAO reportingDAO) {
        List<String> revenueLines = reportingDAO.getRevenueByCategory();
        if (revenueLines.isEmpty()) {
            System.out.println("Chua co du lieu doanh thu.");
            return;
        }
        revenueLines.forEach(System.out::println);
    }

    private static void showOneCategoryRevenue(Scanner scanner, ReportingDAO reportingDAO) {
        System.out.print("Nhap ten danh muc can tinh doanh thu: ");
        String category = scanner.nextLine().trim();
        BigDecimal revenue = reportingDAO.calculateCategoryRevenue(category);
        System.out.println("Tong doanh thu danh muc " + category + ": " + revenue);
    }

    private static void showOrdersWithItems(ReportingDAO reportingDAO) {
        List<Order> orders = reportingDAO.getOrdersWithItems();
        if (orders.isEmpty()) {
            System.out.println("Chua co don hang nao.");
            return;
        }

        for (Order order : orders) {
            System.out.println(
                "Order #" + order.getOrderId()
                    + " | user_id=" + order.getUserId()
                    + " | username=" + order.getUsername()
                    + " | created_at=" + order.getCreatedAt()
            );

            if (order.getItems().isEmpty()) {
                System.out.println("  (Khong co chi tiet don hang)");
                continue;
            }

            for (OrderItem item : order.getItems()) {
                System.out.println(
                    "  - detail_id=" + item.getOrderDetailId()
                        + ", product_id=" + item.getProductId()
                        + ", name=" + item.getProductName()
                        + ", qty=" + item.getQuantity()
                        + ", unit_price=" + item.getUnitPrice()
                );
            }
        }
    }
}
