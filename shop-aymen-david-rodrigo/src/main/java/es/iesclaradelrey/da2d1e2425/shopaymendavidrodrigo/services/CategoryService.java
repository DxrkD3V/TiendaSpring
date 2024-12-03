package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.CategoryRepo;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {
    long count();
    void save(Category category);
    Collection<Category> findAll();
    Optional<Category> findById(long id);
}
