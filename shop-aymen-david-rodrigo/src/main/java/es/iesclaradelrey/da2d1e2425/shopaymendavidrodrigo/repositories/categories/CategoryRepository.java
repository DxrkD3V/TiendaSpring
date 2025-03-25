package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.categories;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsProductByNameIgnoreCase(String name);
    boolean existsCategoryByNameIgnoreCase(String name);
}