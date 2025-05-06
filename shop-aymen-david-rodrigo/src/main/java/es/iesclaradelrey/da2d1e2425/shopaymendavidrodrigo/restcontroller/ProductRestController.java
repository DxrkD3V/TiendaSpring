package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.ProductAPIDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Collection<ProductAPIDTO>> getAllProducts() {
        Collection<Product> products = productService.findAll();
        Collection<ProductAPIDTO> ProductAPIDTOs = products.stream().map(p -> {
            ProductAPIDTO dto = new ProductAPIDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setImageurl(p.getImageurl());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setStock(p.getStock());
            dto.setManufacture(p.getManufacture());
            dto.setMotor(p.getMotor());
            dto.setHp(p.getHp());
            dto.setMaxVelocity(p.getMaxVelocity());
            dto.setCategoryId(p.getCategory().getId());
            return dto;
        }).toList();

        return ResponseEntity.ok(ProductAPIDTOs);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Collection<ProductAPIDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        Collection<Product> products = productService.findByCategoryID(categoryId);

        Collection<ProductAPIDTO> dtos = products.stream().map(p -> {
            ProductAPIDTO dto = new ProductAPIDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setImageurl(p.getImageurl());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setStock(p.getStock());
            dto.setManufacture(p.getManufacture());
            dto.setMotor(p.getMotor());
            dto.setHp(p.getHp());
            dto.setMaxVelocity(p.getMaxVelocity());
            dto.setCategoryId(p.getCategory().getId());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<ProductAPIDTO>> findProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long cat,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productService.searchProducts(search, cat, pageable);
        Page<ProductAPIDTO> dtoPage = page.map(p -> {
            ProductAPIDTO dto = new ProductAPIDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setImageurl(p.getImageurl());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setStock(p.getStock());
            dto.setManufacture(p.getManufacture());
            dto.setMotor(p.getMotor());
            dto.setHp(p.getHp());
            dto.setMaxVelocity(p.getMaxVelocity());
            dto.setCategoryId(p.getCategory() != null ? p.getCategory().getId() : null);
            return dto;
        });
        return ResponseEntity.ok(dtoPage);
    }
}
