package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.restcontroller;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        Collection<Category> categories = categoryService.findAll();

        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(c -> new CategoryDTO(c.getId(), c.getName()))
                .toList();

        return ResponseEntity.ok(categoryDTOs);
    }

}

