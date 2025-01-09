package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    long count();
    void save(Product product);
    Collection<Product> findAll();
    Collection<Product> findByCategoryID(Long categoryId);
}
