package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    public final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public Collection<Category> findAll() {
        return List.of();
    }

    @Override
    public Optional<Category> findById(long id) {
        return Optional.empty();
    }
}
