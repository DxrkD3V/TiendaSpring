package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateProductDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.AlreadyExistException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.products.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        modelAndView.addObject("product", new CreateProductDTO());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView newProduct(@Valid @ModelAttribute("product") CreateProductDTO productDto, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        ModelAndView modelAndView = new ModelAndView("admin/product/new-product");
        modelAndView.addObject("categories", categoryService.findAll());

        if (bindingResult.hasErrors()) {
            if(productService.existsByName(productDto.getName())) {
                bindingResult.rejectValue("name", null, "Ya existe un producto con este nombre");
            }
            return modelAndView;
        }

        try {
            redirAttrs.addFlashAttribute("success", "Producto creado con exito");
            productService.create(productDto);
            return new ModelAndView("redirect:/admin/products");
        } catch (AlreadyExistException e) {
            bindingResult.rejectValue("name", null, e.getMessage());
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("admin/product/new-product");
            modelAndView2.addObject("categories",categoryService.findAll());
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView2;
        }
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {

        try {
            Product product = productService.findByIdThrowException(id);

            CreateProductDTO productDTO = new CreateProductDTO(
                    product.getName(),
                    product.getImageurl(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getManufacture(),
                    product.getMotor(),
                    product.getHp(),
                    product.getMaxVelocity(),
                    product.getCategory()
            );

            ModelAndView modelAndView = new ModelAndView("admin/product/edit-product");
            modelAndView.addObject("product", productDTO);
            modelAndView.addObject("categories", categoryService.findAll());

            return modelAndView;
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/products");
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateProduct(@PathVariable Long id,
                                      @Valid @ModelAttribute("product") CreateProductDTO productDto,
                                      BindingResult binding,RedirectAttributes redirectAttributes) {
        if (binding.hasErrors()) {
            if (productService.existsByName(productDto.getName())) {
                binding.rejectValue("name", null, "Ya existe un producto llamado " + productDto.getName());
            }
            return new ModelAndView("admin/product/edit-product")
                    .addObject("categories", categoryService.findAll())
                    .addObject("productId", id);
        }

        try {
            redirectAttributes.addFlashAttribute("success", "Producto editado con exito");
            productService.update(id, productDto);
            return new ModelAndView("redirect:/admin/products");
        } catch (AlreadyExistException e) {
            binding.rejectValue("name", null, e.getMessage());
            return new ModelAndView("admin/product/edit-product")
                    .addObject("categories", categoryService.findAll())
                    .addObject("productId", id);
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/products");
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("admin/product/edit-product");
            modelAndView.addObject("categories", categoryService.findAll());
            binding.reject("globalError", e.getMessage());
            return modelAndView;
        }
//        return new ModelAndView("/admin/product/edit-product");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id")Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("admin/product/delete-product");
        try{
            Product product = productService.findByIdThrowException(id);
            modelAndView.addObject("product", product);
            return modelAndView;
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/products");
        }
    }

    @PostMapping("delete/{id}")
    public ModelAndView deleteProductPost(@PathVariable Long id,
                                          RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        try{
            productService.delete(id);
            redirectAttributes.addFlashAttribute("success","producto eliminado con éxito");
            return new ModelAndView("redirect:/admin/products");
        }catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return new ModelAndView("admin/product/list-products");
        }catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            ModelAndView modelAndView = new ModelAndView("/admin/product/delete-product");
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView;
        }
    }
}
