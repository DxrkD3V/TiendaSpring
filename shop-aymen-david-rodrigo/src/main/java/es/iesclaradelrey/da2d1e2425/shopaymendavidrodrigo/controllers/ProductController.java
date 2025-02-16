package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collection;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final CategoryService categoryService;


    public ProductController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getProducts(Model model) {
        Collection<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "products";
    }
}
