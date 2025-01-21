package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepo extends ListCrudRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
