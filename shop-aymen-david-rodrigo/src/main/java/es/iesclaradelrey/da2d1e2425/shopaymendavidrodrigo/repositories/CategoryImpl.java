package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CategoryImpl implements CategoryRepo {
    private final Map<UUID, Category> categoryMap = new HashMap<>();

    @Override
    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(UUID.randomUUID());
        }
        categoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }
}