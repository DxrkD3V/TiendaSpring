package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.shoppingCart.ShoppingCartRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart.ShoppingCartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, CategoryService categoryService, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @GetMapping
    public String getProducts(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartDTO cartDTO = shoppingCartService.getCartByEmail(email);
        
        Collection<Product> products = productService.findAll();
        
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("cartDTO", cartDTO);
        model.addAttribute("products", products);
        
        return "shoppingCart";
    }
    
    @GetMapping(value = "/add", params="returnurl")
    public String addProduct (@RequestParam("productId") Long id,
                              @RequestParam("returnurl") String returnUrl) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            shoppingCartService.add(product.get());
        }

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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Collection<ShoppingCart> allCarts = shoppingCartService.findAll();
        Collection<ShoppingCart> userCarts = allCarts.stream()
                .filter(cart -> cart.getUserId() != null &&
                        cart.getUserId().getEmail().equals(email))
                .collect(Collectors.toList());

        shoppingCartRepository.deleteAll(userCarts);

        return "redirect:/shoppingCart";
    }


}