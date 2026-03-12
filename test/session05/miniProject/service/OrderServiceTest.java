package session05.miniProject.service;

import session05.miniProject.modal.*;
import session05.miniProject.repository.*;
import session05.miniProject.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;
    private MenuRepository menuRepository;
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {

        menuRepository = new MenuRepository();
        orderRepository = new OrderRepository();

        orderService = new OrderService(menuRepository, orderRepository);

        // thêm món mẫu vào menu
        MenuItem burger = new Food("F01","Burger",50000,10,false);
        menuRepository.add(burger);
    }

    // Test tạo order
    @Test
    void testCreateOrder() {

        Order order = orderService.createOrder("O01");

        assertNotNull(order);
        assertEquals("O01",order.orderId());
    }

    // Test thêm món vào order
    @Test
    void testAddItemToOrder() throws Exception {

        orderService.createOrder("O01");

        orderService.addItemToOrder("O01","F01",2);

        Order order = orderRepository.findById("O01").get();

        assertEquals(1,order.items().size());
        assertEquals(2,order.items().get(0).quantity());
    }

    // Test lỗi khi không đủ tồn kho
    @Test
    void testAddItemNotEnoughStock() throws Exception {

        orderService.createOrder("O01");

        assertThrows(InsufficientStockException.class, () -> {
            orderService.addItemToOrder("O01","F01",100);
        });
    }

    // Test tính tổng tiền
    @Test
    void testCalculateTotal() throws Exception {

        orderService.createOrder("O01");

        orderService.addItemToOrder("O01","F01",2);

        double total = orderService.calculateTotal("O01");

        assertEquals(100000,total);
    }

    // Test áp dụng discount
    @Test
    void testApplyDiscount() throws Exception {

        orderService.createOrder("O01");

        orderService.addItemToOrder("O01","F01",2);

        orderService.applyDiscount("O01",10,5000);

        double total = orderService.calculateTotal("O01");

        assertEquals(95000,total);
    }

    // Test update status
    @Test
    void testUpdateStatus() throws Exception {

        orderService.createOrder("O01");

        orderService.updateStatus("O01",Order.Status.PAID);

        Order order = orderRepository.findById("O01").get();

        assertEquals(Order.Status.PAID,order.status());
    }

    // Test lấy tất cả orders
    @Test
    void testGetAllOrders() {

        orderService.createOrder("O01");
        orderService.createOrder("O02");

        assertEquals(2,orderService.getAllOrders().size());
    }

    // Test lỗi orderId không tồn tại
    @Test
    void testInvalidOrderId() {

        assertThrows(InvalidOrderIdException.class, () -> {
            orderService.calculateTotal("NOT_EXIST");
        });
    }
}