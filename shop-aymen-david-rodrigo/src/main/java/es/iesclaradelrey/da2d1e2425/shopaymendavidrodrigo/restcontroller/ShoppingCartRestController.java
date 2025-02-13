package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.AddItemCartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody AddItemCartDTO productDto) throws Exception {
            shoppingCartService.saveOrUpdate(productDto.getProductId(),productDto.getAddUnits());
            return ResponseEntity.ok("Proucto añadido");
    }


}
