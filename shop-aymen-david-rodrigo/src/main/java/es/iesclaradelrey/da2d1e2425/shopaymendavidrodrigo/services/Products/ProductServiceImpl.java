package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Products.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
private ProductRepo productRepo;
public ProductServiceImpl(ProductRepo productRepo) {
    this.productRepo = productRepo;
}
    @Override
    public long count() {
        return productRepo.count() ;
    }

    @Override
    public void save(Product product) {
    productRepo.save(product);
    }

    @Override
    public Collection<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Collection<Product> findByCategoryID(Long categoryId) {
    return productRepo.findAll().stream().filter(product -> product.getCategory().getId().equals(categoryId)).collect(Collectors.toList());

    }
}
