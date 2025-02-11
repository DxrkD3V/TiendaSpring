package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
private final ProductRepository productRepository;
public ProductServiceImpl(ProductRepository productRepo) {
    this.productRepository = productRepo;
}
    @Override
    public long count() {
        return productRepository.count() ;
    }

    @Override
    public void save(Product product) {
    productRepository.save(product);
    }

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> findByCategoryID(Long categoryId) {
    return productRepository.findAll().stream().filter(product -> product.getCategory().getId().equals(categoryId)).collect(Collectors.toList());

    }
    @Override
    public Collection<Product> findByName(String name) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        return products;
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
