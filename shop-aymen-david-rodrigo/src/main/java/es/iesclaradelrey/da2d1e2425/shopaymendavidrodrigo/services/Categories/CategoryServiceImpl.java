package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Categories;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Categories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public long count() {
        return categoryRepo.count();
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }
}
