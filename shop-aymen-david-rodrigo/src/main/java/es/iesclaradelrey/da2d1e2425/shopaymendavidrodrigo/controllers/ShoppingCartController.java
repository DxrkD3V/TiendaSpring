package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Categories.CategoryService;
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
    private final CategoryService categoryService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, CategoryService categoryService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getProducts(Model model) {
        Collection<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        Collection<Product> products = productService.findAll();
        double total = shoppingCarts.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getUnits())
                .sum();
        model.addAttribute("categories", categoryService.findAll());
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

        if(shoppingCart.isPresent()) {
            if(shoppingCart.get().getUnits() > 1) {
                shoppingCart.get().setUnits(shoppingCart.get().getUnits() - 1);
                shoppingCartService.save(shoppingCart.get());
            }else{
                shoppingCartService.remove(shoppingCart.get());
            }
        }
        return "redirect:" + returnUrl;
    }

    @GetMapping("/removeAll")
    public String removeAllProducts() {
        Collection<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        for(ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartService.remove(shoppingCart);
        }
        return "redirect:/shoppingCart";
    }

}
