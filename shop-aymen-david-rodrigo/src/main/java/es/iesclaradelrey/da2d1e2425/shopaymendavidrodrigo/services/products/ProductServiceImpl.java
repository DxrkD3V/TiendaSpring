package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.AlreadyExistException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.categories.CategoryRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products.ProductRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
private final ProductRepository productRepository;
private final CategoryRepository categoryRepository;

public ProductServiceImpl(ProductRepository productRepo, CategoryRepository categoryRepository) {
    this.productRepository = productRepo;
    this.categoryRepository = categoryRepository;
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

    @Override
    public Product findByIdThrowException(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("el producto no existe"));
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsProductByNameIgnoreCase(name);
    }

    @Override
    public Long create(CreateProductDTO productDto) {
        if (productRepository.existsProductByNameIgnoreCase(productDto.getName())) {
            throw new AlreadyExistException(String.format("Ya existe un producto con el nombre %s", productDto.getName()));
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageurl(productDto.getImageurl());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        product.setManufacture(productDto.getManufacture());
        product.setMotor(productDto.getMotor());
        product.setHp(productDto.getHp());
        product.setMaxVelocity(productDto.getMaxVelocity());

        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }

    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir) {
        Sort.Direction direction = orderDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(direction, orderBy));

        return productRepository.findAll(pageable);
    }
    @Override
    public void update(Long id, CreateProductDTO productDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado."));

        if (!product.getName().equalsIgnoreCase(productDTO.getName())) {
            boolean exists = productRepository.existsProductByNameIgnoreCase(productDTO.getName());
            if (exists) {
                throw new AlreadyExistException("El producto ya existe con ese nombre.");
            }
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada.")));
        product.setManufacture(productDTO.getManufacture());
        product.setMotor(productDTO.getMotor());
        product.setHp(productDTO.getHp());
        product.setMaxVelocity(productDTO.getMaxVelocity());


        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El producto con ID: " + id + " no existe"));

        productRepository.delete(product);
    }

}
