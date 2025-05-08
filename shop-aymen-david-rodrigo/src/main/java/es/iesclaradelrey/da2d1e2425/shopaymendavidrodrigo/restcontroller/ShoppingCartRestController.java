package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/")
    public ResponseEntity<CartDTO> getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartDTO cart = shoppingCartService.getCartByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
    @PostMapping("/{productId}")
    public ResponseEntity<CartDTO> addSingleUnitToCart(@PathVariable Long productId) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        shoppingCartService.saveOrUpdate(productId, 1, email);
        CartDTO cart = shoppingCartService.getCartByEmail(email);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{productId}/{count}")
    public ResponseEntity<CartDTO> addProduct(@PathVariable Long productId, @PathVariable int count) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        shoppingCartService.saveOrUpdate(productId, count, email);
        CartDTO cart = shoppingCartService.getCartByEmail(email);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<CartDTO> removeProduct(@PathVariable Long productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        shoppingCartService.delete(productId, email);
        CartDTO cart = shoppingCartService.getCartByEmail(email);
        return ResponseEntity.ok(cart);
    }
}
