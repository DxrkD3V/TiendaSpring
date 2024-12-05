package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Products.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public Collection<Category> findAll() {
        return List.of();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }
}
