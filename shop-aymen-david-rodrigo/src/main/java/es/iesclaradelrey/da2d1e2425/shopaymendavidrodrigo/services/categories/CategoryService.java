package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateCategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import org.springframework.data.domain.Page;
import java.util.Collection;
import java.util.Optional;

public interface CategoryService {
    long count();
    void save(Category category);
    Collection<Category> findAll();
    Optional<Category> findById(Long id);
    boolean existsByName(String name);
    void create (CreateCategoryDTO createCategoryDTO);

    Page<Category> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);
    void update(Long id, CreateCategoryDTO createCategoryDTO);
    void delete(Long id);
}
