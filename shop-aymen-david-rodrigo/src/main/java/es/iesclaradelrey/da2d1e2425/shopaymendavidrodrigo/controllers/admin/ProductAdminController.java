package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDto;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("admin/products")

public class ProductAdminController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductAdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String index(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "asc") String orderDir,
            Model model) {

        Page<Product> productPage = productService.findAll(pageNumber, pageSize, orderBy, orderDir);

        List<String> atributos = List.of("Name", "Description");

        model.addAttribute("atributos", atributos);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);

        return "admin/product/list-products";
    }

    @GetMapping("/new")
    public ModelAndView newProduct() {
        Collection<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/product/new-product");
        modelAndView.addObject("product", new CreateProductDto());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView newProduct(@Valid @ModelAttribute("product") CreateProductDto productDto, BindingResult result) {
        if (result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/product/new-product");
            modelAndView.addObject("categories", categoryService.findAll());
            return modelAndView;
        }
        Long productId = productService.create(productDto);
        return new ModelAndView("redirect:/categories/product/" + productId);
    }
}
