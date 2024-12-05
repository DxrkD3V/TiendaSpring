package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    long count();
    void save(Category category);
    Collection<Category> findAll();
    Optional<Category> findById(Long id);
}
