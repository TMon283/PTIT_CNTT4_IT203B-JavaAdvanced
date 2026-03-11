package session05.miniProject.ui;

import session05.miniProject.exception.InsufficientStockException;
import session05.miniProject.exception.InvalidOrderIdException;
import session05.miniProject.modal.Drink;
import session05.miniProject.modal.MenuItem;
import session05.miniProject.modal.Order;
import session05.miniProject.repository.MenuRepository;
import session05.miniProject.repository.OrderRepository;
import session05.miniProject.service.MenuService;
import session05.miniProject.service.OrderService;
import session05.miniProject.service.StatisticService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final MenuService menuService;
    private final OrderService orderService;
    private final StatisticService statisticService;

    public MainMenu() {
        MenuRepository menuRepository = new MenuRepository();
        OrderRepository orderRepository = new OrderRepository();
        this.menuService = new MenuService(menuRepository);
        this.orderService = new OrderService(menuRepository, orderRepository);
        this.statisticService = new StatisticService(menuRepository, orderRepository);
    }

    public static void main(String[] args) {
        new MainMenu().run();
    }

    public void run() {
        int choice;
        do {
            System.out.println("===== HỆ THỐNG ORDER =====");
            System.out.println("1. Quản lý thực đơn");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Thống kê");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = readInt();
            switch (choice) {
                case 1:
                    menuManagement();
                    break;
                case 2:
                    orderManagement();
                    break;
                case 3:
                    statisticMenu();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }

    private void menuManagement() {
        int choice;
        do {
            System.out.println("===== QUẢN LÝ THỰC ĐƠN =====");
            System.out.println("1. Thêm món");
            System.out.println("2. Sửa món");
            System.out.println("3. Xóa món");
            System.out.println("4. Tìm theo tên");
            System.out.println("5. Hiển thị thực đơn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    addMenuItemUI();
                    break;
                case 2:
                    updateMenuItemUI();
                    break;
                case 3:
                    deleteMenuItemUI();
                    break;
                case 4:
                    findMenuItemByNameUI();
                    break;
                case 5:
                    menuService.displayMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (true);
    }

    private void addMenuItemUI() {
        System.out.println("Chọn loại món: 1.Food 2.Drink 3.Dessert");
        int type = readInt();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Tên: ");
        String name = scanner.nextLine();
        System.out.print("Giá cơ bản: ");
        double price = readDouble();
        System.out.print("Tồn kho: ");
        int stock = readInt();

        MenuItem item = switch (type) {
            case 1 -> {
                System.out.print("Có cay không? (true/false): ");
                boolean spicy = Boolean.parseBoolean(scanner.nextLine());
                yield menuService.createFood(id, name, price, stock, spicy);
            }
            case 2 -> {
                System.out.print("Size (S/M/L): ");
                String sizeStr = scanner.nextLine().trim().toUpperCase();
                Drink.Size size = Drink.Size.valueOf(sizeStr);
                yield menuService.createDrink(id, name, price, stock, size);
            }
            case 3 -> {
                System.out.print("Có lạnh không? (true/false): ");
                boolean cold = Boolean.parseBoolean(scanner.nextLine());
                yield menuService.createDessert(id, name, price, stock, cold);
            }
            default -> null;
        };

        if (item != null) {
            menuService.addMenuItem(item);
            System.out.println("Thêm món thành công.");
        } else {
            System.out.println("Loại món không hợp lệ.");
        }
    }

    private void updateMenuItemUI() {
        System.out.print("Nhập ID món cần sửa: ");
        String id = scanner.nextLine();
        List<MenuItem> all = menuService.getAll();
        MenuItem old = all.stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
        if (old == null) {
            System.out.println("Không tìm thấy món.");
            return;
        }
        System.out.print("Tên mới (" + old.getName() + "): ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = old.getName();
        System.out.print("Giá cơ bản mới (" + old.getBasePrice() + "): ");
        String priceStr = scanner.nextLine();
        double price = priceStr.isEmpty() ? old.getBasePrice() : Double.parseDouble(priceStr);
        System.out.print("Tồn kho mới (" + old.getStock() + "): ");
        String stockStr = scanner.nextLine();
        int stock = stockStr.isEmpty() ? old.getStock() : Integer.parseInt(stockStr);

        old.setName(name);
        old.setBasePrice(price);
        old.setStock(stock);
        boolean ok = menuService.updateMenuItem(old);
        System.out.println(ok ? "Cập nhật thành công." : "Cập nhật thất bại.");
    }

    private void deleteMenuItemUI() {
        System.out.print("Nhập ID món cần xóa: ");
        String id = scanner.nextLine();
        boolean ok = menuService.deleteMenuItem(id);
        System.out.println(ok ? "Đã xóa." : "Không tìm thấy món.");
    }

    private void findMenuItemByNameUI() {
        System.out.print("Nhập tên hoặc từ khóa: ");
        String keyword = scanner.nextLine();
        List<MenuItem> result = menuService.findByName(keyword);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy món phù hợp.");
        } else {
            result.forEach(System.out::println);
        }
    }

    private void orderManagement() {
        int choice;
        do {
            System.out.println("===== QUẢN LÝ ĐƠN HÀNG =====");
            System.out.println("1. Tạo đơn hàng mới");
            System.out.println("2. Thêm món vào đơn");
            System.out.println("3. Áp dụng giảm giá / phí dịch vụ");
            System.out.println("4. Cập nhật trạng thái đơn");
            System.out.println("5. Tính tổng tiền đơn");
            System.out.println("6. Hiển thị tất cả đơn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            try {
                switch (choice) {
                    case 1:
                        createOrderUI();
                        break;
                    case 2:
                        addItemToOrderUI();
                        break;
                    case 3:
                        applyDiscountUI();
                        break;
                    case 4:
                        updateOrderStatusUI();
                        break;
                    case 5:
                        calculateTotalUI();
                        break;
                    case 6:
                        showAllOrdersUI();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (InvalidOrderIdException | InsufficientStockException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        } while (true);
    }

    private void createOrderUI() {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        Order order = orderService.createOrder(orderId);
        System.out.println("Đã tạo đơn: " + order.orderId());
    }

    private void addItemToOrderUI() throws InvalidOrderIdException, InsufficientStockException {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        System.out.print("Nhập ID món: ");
        String itemId = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int quantity = readInt();
        orderService.addItemToOrder(orderId, itemId, quantity);
        System.out.println("Đã thêm món vào đơn.");
    }

    private void applyDiscountUI() throws InvalidOrderIdException {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        System.out.print("Nhập % giảm giá: ");
        double discount = readDouble();
        System.out.print("Nhập phí dịch vụ: ");
        double fee = readDouble();
        orderService.applyDiscount(orderId, discount, fee);
        System.out.println("Đã cập nhật giảm giá/phí dịch vụ.");
    }

    private void updateOrderStatusUI() throws InvalidOrderIdException {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        System.out.println("Trạng thái: 1.PENDING 2.PAID 3.CANCELLED");
        int s = readInt();
        Order.Status status = switch (s) {
            case 2 -> Order.Status.PAID;
            case 3 -> Order.Status.CANCELLED;
            default -> Order.Status.PENDING;
        };
        orderService.updateStatus(orderId, status);
        System.out.println("Đã cập nhật trạng thái.");
    }

    private void calculateTotalUI() throws InvalidOrderIdException {
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        double total = orderService.calculateTotal(orderId);
        System.out.printf("Tổng tiền đơn %s: %.2f%n", orderId, total);
    }

    private void showAllOrdersUI() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Chưa có đơn hàng nào.");
            return;
        }
        for (Order o : orders) {
            System.out.printf("Đơn %s - trạng thái: %s - số món: %d - tổng (chưa giảm/phi): %.2f%n",
                    o.orderId(), o.status(), o.items().size(), o.totalPrice());
        }
    }

    private void statisticMenu() {
        int choice;
        do {
            System.out.println("===== THỐNG KÊ =====");
            System.out.println("1. Tìm món theo khoảng giá");
            System.out.println("2. Tính tổng doanh thu");
            System.out.println("3. Món bán chạy nhất");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    findItemByPriceRangeUI();
                    break;
                case 2:
                    totalRevenueUI();
                    break;
                case 3:
                    bestSellingItemsUI();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (true);
    }

    private void findItemByPriceRangeUI() {
        System.out.print("Giá từ: ");
        double min = readDouble();
        System.out.print("Đến: ");
        double max = readDouble();
        List<MenuItem> items = statisticService.findItemsByPriceRange(min, max);
        if (items.isEmpty()) {
            System.out.println("Không có món trong khoảng giá.");
        } else {
            items.forEach(System.out::println);
        }
    }

    private void totalRevenueUI() {
        double revenue = statisticService.calculateTotalRevenue();
        System.out.printf("Tổng doanh thu: %.2f%n", revenue);
    }

    private void bestSellingItemsUI() {
        System.out.print("Lấy top bao nhiêu món?: ");
        int top = readInt();
        List<MenuItem> items = statisticService.getBestSellingItems(top);
        if (items.isEmpty()) {
            System.out.println("Chưa có dữ liệu bán hàng.");
        } else {
            System.out.println("Món bán chạy nhất:");
            items.forEach(System.out::println);
        }
    }

    private int readInt() {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số nguyên: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số: ");
            }
        }
    }
}
