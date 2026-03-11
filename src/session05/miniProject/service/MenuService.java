package session05.miniProject.service;

import session05.miniProject.modal.Dessert;
import session05.miniProject.modal.Drink;
import session05.miniProject.modal.Food;
import session05.miniProject.modal.MenuItem;
import session05.miniProject.repository.MenuRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void addMenuItem(MenuItem item) {
        menuRepository.add(item);
    }

    public boolean updateMenuItem(MenuItem item) {
        return menuRepository.update(item);
    }

    public boolean deleteMenuItem(String id) {
        return menuRepository.delete(id);
    }

    public List<MenuItem> findByName(String keyword) {
        String kw = keyword.toLowerCase(Locale.ROOT);
        return menuRepository.findAll().stream()
                .filter(i -> i.getName().toLowerCase(Locale.ROOT).contains(kw))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getAll() {
        return menuRepository.findAll();
    }

    public void displayMenu() {
        System.out.println("===== THỰC ĐƠN =====");
        if (menuRepository.findAll().isEmpty()) {
            System.out.println("Chưa có món nào.");
            return;
        }
        menuRepository.findAll().forEach(System.out::println);
    }

    public Food createFood(String id, String name, double basePrice, int stock, boolean spicy) {
        return new Food(id, name, basePrice, stock, spicy);
    }

    public Drink createDrink(String id, String name, double basePrice, int stock, Drink.Size size) {
        return new Drink(id, name, basePrice, stock, size);
    }

    public Dessert createDessert(String id, String name, double basePrice, int stock, boolean cold) {
        return new Dessert(id, name, basePrice, stock, cold);
    }
}
