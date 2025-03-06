package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Collection;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;


    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        Collection<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "products";
    }

    
    @GetMapping("/new")
    public ModelAndView newProduct() {
        Collection<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("new-product");
        modelAndView.addObject("product", new CreateProductDto());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView newProduct(@ModelAttribute CreateProductDto productDto) {
        Long productId = productService.create(productDto);
        return new ModelAndView("redirect:/categories/product/" + productId);
    }
}
