package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import org.springframework.data.domain.Page;

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
    boolean existsByName(String name);
    Optional<Product> findById(Long id);
    Long create(CreateProductDTO createProductDto);
    void update(Long id,CreateProductDTO createProductDto);

    Page<Product> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);

}
