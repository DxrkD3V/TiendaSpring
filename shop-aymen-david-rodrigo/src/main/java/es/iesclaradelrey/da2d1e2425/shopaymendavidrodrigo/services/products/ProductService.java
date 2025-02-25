package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    long count();
    void save(Product product);
    Collection<Product> findAll();
    Collection<Product> findByCategoryID(Long categoryId);
    Collection<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String query);

    Optional<Product> findById(Long id);
    void create(CreateProductDto createProductDto);
}
