package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateCategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;
    public CategoryAdminController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String index(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "asc") String orderDir,
            Model model) {

        Page<Category> categoryPage = categoryService.findAll(pageNumber, pageSize, orderBy, orderDir);

        List<String> atributos = List.of("Name", "Description");

        model.addAttribute("atributos", atributos);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);

        return "admin/category/list-categories";
    }

    @GetMapping("/new")
    public ModelAndView newCategory(){
        ModelAndView modelAndView = new ModelAndView("admin/category/new-category");
        modelAndView.addObject("category", new CreateCategoryDTO());
        return modelAndView;
    }
    @PostMapping("/new")
    public ModelAndView newCategory(@Valid @ModelAttribute("category") CreateCategoryDTO categoryDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("admin/category/new-category");
        }

        categoryService.create(categoryDTO);
        return new ModelAndView("redirect:/categories/");
    }
}
