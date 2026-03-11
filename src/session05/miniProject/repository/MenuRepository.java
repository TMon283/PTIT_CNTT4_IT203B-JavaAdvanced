package session05.miniProject.repository;

import session05.miniProject.modal.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuRepository {

    private final List<MenuItem> items = new ArrayList<>();

    public List<MenuItem> findAll() {
        return Collections.unmodifiableList(items);
    }

    public Optional<MenuItem> findById(String id) {
        return items.stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void add(MenuItem item) {
        items.add(item);
    }

    public boolean delete(String id) {
        return items.removeIf(i -> i.getId().equalsIgnoreCase(id));
    }

    public boolean update(MenuItem updated) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equalsIgnoreCase(updated.getId())) {
                items.set(i, updated);
                return true;
            }
        }
        return false;
    }
}
