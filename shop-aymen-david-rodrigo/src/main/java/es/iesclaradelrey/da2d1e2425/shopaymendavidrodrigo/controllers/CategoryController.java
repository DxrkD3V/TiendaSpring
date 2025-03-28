package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductServiceImpl productServiceImpl) {
        this.categoryService = categoryService;
        this.productService = productServiceImpl;
    }

    @GetMapping
    public String getCategories(Model model) {
        Collection<Category> categories = categoryService.findAll();
        Collection<Product> products = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "categories";
    }

    @GetMapping("category/{id}")
    public String getCategoryProducts(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        Collection<Category> allCategories = categoryService.findAll();

        model.addAttribute("products", category.get().getProducts());
        model.addAttribute("categories", allCategories);
        model.addAttribute("category", category.get());

        return "category-products";
    }

    @GetMapping("product/{id}")
    public String getProducts(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        Collection<Category> allCategories = categoryService.findAll();

        Collection<Rating> ratings = product.get().getRatings();

        double averageRating = ratings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);

        model.addAttribute("product", product.get());
        model.addAttribute("ratings", product.get().getRatings());
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("categories", allCategories);

        return "product";
    }

}