package session05.miniProject.service;

import session05.miniProject.modal.*;
import session05.miniProject.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticServiceTest {
    private StatisticService statisticService;
    private MenuRepository menuRepository;
    private OrderRepository orderRepository;
    private OrderService orderService;
    @BeforeEach
    void setUp() {

        menuRepository = new MenuRepository();
        orderRepository = new OrderRepository();
        orderService = new OrderService(menuRepository, orderRepository);

        statisticService = new StatisticService(menuRepository, orderRepository);

        MenuItem burger = new Food("F01","Burger",50000,20,false);
        MenuItem pizza = new Food("F02","Pizza",70000,20,false);
        MenuItem chicken = new Food("F03","Chicken",60000,20,false);

        menuRepository.add(burger);
        menuRepository.add(pizza);
        menuRepository.add(chicken);

        orderService.createOrder("O01");

        try {
            orderService.addItemToOrder("O01","F01",5); // burger
            orderService.addItemToOrder("O01","F02",2); // pizza
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindItemsByPriceRange() {

        List<MenuItem> result = statisticService.findItemsByPriceRange(55000,75000);

        assertEquals(2,result.size());
    }

    // Test tính tổng doanh thu
    @Test
    void testCalculateTotalRevenue() {

        double revenue = statisticService.calculateTotalRevenue();

        assertEquals(390000,revenue);
    }

    // Test lấy món bán chạy nhất
    @Test
    void testGetBestSellingItems() {

        List<MenuItem> best = statisticService.getBestSellingItems(1);

        assertEquals("Burger",best.get(0).getName());
    }

    // Test top 2 món bán chạy
    @Test
    void testTopTwoBestSellingItems() {

        List<MenuItem> best = statisticService.getBestSellingItems(2);

        assertEquals(2,best.size());
    }
}
