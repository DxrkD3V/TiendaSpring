package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    boolean existsProductByNameIgnoreCase(String name);
    @Query("""
    SELECT p FROM Product p
    WHERE 
    (:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) 
        OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')))
    AND (:cat IS NULL OR p.category.id = :cat)""")
    Page<Product> findBySearchAndCategory(@Param("search") String search, @Param("cat") Long cat, Pageable pageable);
}
