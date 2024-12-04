package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.commandlinerunner;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriesInit implements CommandLineRunner {
    private final CategoryService categoryService;

    public CategoriesInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {

        Category category1 = new Category(1L, "4x4", "Coches Grandes 4x4",null);
        Category category2 = new Category(2L, "Electricos", "Coches electricos",null);


        categoryService.save(category1);
        categoryService.save(category2);

    }
}
