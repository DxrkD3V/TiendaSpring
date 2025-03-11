package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    boolean existsProductByNameIgnoreCase(String name);
}
