package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.AddItemCartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody AddItemCartDTO productDto) throws Exception {
            shoppingCartService.saveOrUpdate(productDto.getProductId(),productDto.getAddUnits());
            return ResponseEntity.status(HttpStatus.OK).body("producto añadido");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeProduct(@RequestBody AddItemCartDTO productDto) throws Exception {
        shoppingCartService.delete(productDto.getProductId());
        return ResponseEntity.status(HttpStatus.OK).body("producto eliminado");
    }

}
