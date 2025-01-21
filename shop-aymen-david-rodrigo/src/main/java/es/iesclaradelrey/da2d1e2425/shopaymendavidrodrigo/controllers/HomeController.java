package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Ratings.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class HomeController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final RatingService ratingService;

    public HomeController(CategoryService categoryService, ProductService productService, RatingService ratingService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.ratingService = ratingService;
    }

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        List<Product> products = (List<Product>) productService.findAll();
        Collections.shuffle(products);
        List<Product> shuffledProducts = products.subList(0, Math.min(3, products.size()));
        model.addAttribute("products", shuffledProducts);

        List<Rating> ratings = (List<Rating>) ratingService.findAll();
        Collections.shuffle(ratings);
        List<Rating>suffledRatings = ratings.subList(0, Math.min(3, ratings.size()));
        model.addAttribute("ratings", suffledRatings);

        return "index";
    }

    //REPONSEENTITY POR QUE SI NO, DEVUELVE SEARCH.HTML... Y yo solo quiero devolver la query
    @GetMapping("/search")
    public ResponseEntity<List<Product>> buscarProductos(@RequestParam String query) {
        List<Product> products = productService.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(products);
    }
}