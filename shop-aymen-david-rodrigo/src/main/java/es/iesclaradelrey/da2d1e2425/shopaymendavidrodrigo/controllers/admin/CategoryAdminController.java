package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;
    public CategoryAdminController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/",""})
    public String index(@RequestParam(defaultValue = "1")Integer pageNumber,
                        @RequestParam(defaultValue = "5")Integer sizePage,
                        @RequestParam(defaultValue = "name") String orderBy,
                        @RequestParam(defaultValue = "asc") String orderDir,
                        Model model) {

        Collection<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "admin/category/list-categories";
    }


}
