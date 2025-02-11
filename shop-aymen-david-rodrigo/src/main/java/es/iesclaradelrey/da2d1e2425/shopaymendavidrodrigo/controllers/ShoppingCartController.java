package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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

        double total = shoppingCartService.sumTotalPrice(shoppingCarts);

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("totalPrice", total);
        model.addAttribute("products", products);
        return "shoppingCart";
    }

    @GetMapping(value = "/add", params="returnurl")
    public String addProduct (@RequestParam("productId") Long id,
                              @RequestParam("returnurl") String returnUrl) {
        shoppingCartService.add(id);

        return "redirect:" + returnUrl;
    }

    @GetMapping(value = "/remove", params="returnurl")
    public String removeProduct(@RequestParam("productId") Long id,
                                @RequestParam("returnurl") String returnUrl) {
        shoppingCartService.remove(id);

        return "redirect:" + returnUrl;
    }

    @GetMapping("/removeAll")
    public String removeAllProducts() {
        shoppingCartService.removeAll();

        return "redirect:/shoppingCart";
    }

}
