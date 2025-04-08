package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.ProductAPIDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
