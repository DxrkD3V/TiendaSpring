package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
