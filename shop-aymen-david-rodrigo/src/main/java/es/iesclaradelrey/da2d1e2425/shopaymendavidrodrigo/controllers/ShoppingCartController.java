package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.ShoppingCart.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        Collection<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        Collection<Product> products = productService.findAll();
        double total = shoppingCarts.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getUnits())
                .sum();

        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("totalPrice", total);
        model.addAttribute("products", products);
        return "shoppingCart";
    }

    @GetMapping(value = "/add", params="returnurl")
    public String addProduct (@RequestParam("productId") Long id,
                              @RequestParam("returnurl") String returnUrl) {
        Product product = productService.findById(id).orElse(null);
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findByProductID(id);
        if (shoppingCart.isPresent()) {
            shoppingCartService.sumUnits(shoppingCart.get());
        } else {
            ShoppingCart newShoppingCart = new ShoppingCart(1, product);
            shoppingCartService.save(newShoppingCart);

        }
        return "redirect:" + returnUrl;
    }
    @GetMapping(value = "/remove", params="returnurl")
    public String removeProduct(@RequestParam("productId") Long id,
                                @RequestParam("returnurl") String returnUrl) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findByProductID(id);
        shoppingCart.ifPresent(item -> {
            if (item.getUnits() > 1) {
                item.setUnits(item.getUnits() - 1);
                shoppingCartService.save(item);
            } else {
                shoppingCartService.remove(item);
            }
        });
        return "redirect:" + returnUrl;
    }

}
