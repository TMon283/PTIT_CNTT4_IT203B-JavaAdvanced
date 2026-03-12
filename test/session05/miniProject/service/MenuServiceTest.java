package session05.miniProject.service;

import static org.junit.jupiter.api.Assertions.*;
import session05.miniProject.modal.*;
import session05.miniProject.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MenuServiceTest {

    private MenuService menuService;

    @BeforeEach
    void setup() {
        MenuRepository repo = new MenuRepository();
        menuService = new MenuService(repo);
    }

    @Test
    void testAddMenuItem() {

        Food food = new Food("F01","Burger",50000,10,false);

        menuService.addMenuItem(food);

        List<MenuItem> items = menuService.getAll();

        assertEquals(1,items.size());
        assertEquals("Burger",items.get(0).getName());
    }

    @Test
    void testUpdateMenuItem() {

        Food food = new Food("F01","Burger",50000,10,false);
        menuService.addMenuItem(food);

        food.setName("Cheese Burger");

        boolean result = menuService.updateMenuItem(food);

        assertTrue(result);
        assertEquals("Cheese Burger",menuService.getAll().get(0).getName());
    }

    @Test
    void testDeleteMenuItem() {

        Food food = new Food("F01","Burger",50000,10,false);
        menuService.addMenuItem(food);

        boolean result = menuService.deleteMenuItem("F01");

        assertTrue(result);
        assertEquals(0,menuService.getAll().size());
    }

    @Test
    void testFindByName() {

        menuService.addMenuItem(new Food("F01","Burger",50000,10,false));
        menuService.addMenuItem(new Food("F02","Pizza",70000,10,false));

        List<MenuItem> result = menuService.findByName("burger");

        assertEquals(1,result.size());
        assertEquals("Burger",result.get(0).getName());
    }

    @Test
    void testGetAll() {

        menuService.addMenuItem(new Food("F01","Burger",50000,10,false));
        menuService.addMenuItem(new Food("F02","Pizza",70000,10,false));

        List<MenuItem> items = menuService.getAll();

        assertEquals(2,items.size());
    }

    @Test
    void testCreateFood() {

        Food food = menuService.createFood("F03","Chicken",60000,10,true);

        assertEquals("Chicken",food.getName());
        assertTrue(food.isSpicy());
    }

    @Test
    void testCreateDrink() {

        Drink drink = menuService.createDrink("D01","Coca",20000,10, Drink.Size.M);

        assertEquals("Coca",drink.getName());
        assertEquals(Drink.Size.M,drink.getSize());
    }
}