package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.controllers.admin;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateCategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.AlreadyExistException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.CategoryHasRelatedProducts;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView newCategory(@Valid @ModelAttribute("category") CreateCategoryDTO categoryDTO, BindingResult bindingResult, RedirectAttributes redirAttrs ) {
        if (bindingResult.hasErrors()) {
            if(categoryService.existsByName(categoryDTO.getName())) {
                bindingResult.rejectValue("name", null, "El nombre de la categoria ya existe");
            }
            return new ModelAndView("admin/category/new-category");
        }

        try {
            categoryService.create(categoryDTO);
            redirAttrs.addFlashAttribute("success", "Categoria creada con exito");
            return new ModelAndView("redirect:/admin/categories");
        } catch (AlreadyExistException e) {
            bindingResult.rejectValue("name", null, e.getMessage());
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("admin/category/new-category");
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView;
        }
        return new ModelAndView("admin/category/new-category");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCategory(@PathVariable("id") Long id,
                                     RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.findByIdThrowException(id);
            CreateCategoryDTO categoryDTO = new CreateCategoryDTO(category.getName(), category.getDescription(), category.getImage());
            ModelAndView modelAndView = new ModelAndView("admin/category/edit-category");
            modelAndView.addObject("category", categoryDTO);
            return modelAndView;
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/categories");
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCategory(@PathVariable Long id,
                                       @Valid @ModelAttribute("category") CreateCategoryDTO categoryDto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if(categoryService.existsByName(categoryDto.getName())) {
                bindingResult.rejectValue("name", null, "El nombre de la categoria ya existe");
            }
            return new ModelAndView("admin/category/edit-category")
                    .addObject("categoryId", id);
        }
        try {
            categoryService.update(id, categoryDto);
            redirectAttributes.addFlashAttribute("success", "Categoria actualizada con exito");
            return new ModelAndView("redirect:/admin/categories");
        }catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/categories");
        } catch (AlreadyExistException e){
            bindingResult.rejectValue("name", null, e.getMessage());
        }catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView;
        }
        return new ModelAndView("admin/category/edit-category").addObject("categoryId", id);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") Long id,
                                       RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/category/delete-category");;
        try {
            Category category = categoryService.findByIdThrowException(id);
            modelAndView.addObject("category", category);
            return modelAndView;
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/admin/categories");
        }
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCategoryPost(@PathVariable Long id, RedirectAttributes redirectAttributes,BindingResult bindingResult) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Categoria eliminada con exito");
            return new ModelAndView("redirect:/admin/categories");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return new ModelAndView("admin/category/list-categories");
        } catch (CategoryHasRelatedProducts e) {
            ModelAndView modelAndView = new ModelAndView("/admin/category/delete-category");
            Optional<Category> category = categoryService.findById(id);
            modelAndView.addObject("category", category.get());
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            ModelAndView modelAndView = new ModelAndView();
            bindingResult.reject("globalError", e.getMessage());
            return modelAndView;
        }
    }
}
