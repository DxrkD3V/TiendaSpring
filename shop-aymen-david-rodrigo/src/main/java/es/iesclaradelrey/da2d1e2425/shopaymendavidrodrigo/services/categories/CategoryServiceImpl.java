package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateCategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.AlreadyExistException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.CategoryHasRelatedProducts;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.categories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepository = categoryRepo;
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByIdThrowException(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La categoria con ID: " + id + " no existe"));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }


    @Override
    public Page<Category> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir) {
        Sort.Direction direction = orderDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(direction, orderBy));

        return categoryRepository.findAll(pageable);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsCategoryByNameIgnoreCase(name);
    }

    @Override
    public void create(CreateCategoryDTO createCategoryDTO){
        if (categoryRepository.existsCategoryByNameIgnoreCase(createCategoryDTO.getName())) {
           throw new AlreadyExistException(String.format("Ya existe una categoria con el nombre %s", createCategoryDTO.getName()));
        }
        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        category.setDescription(createCategoryDTO.getDescription());
        category.setImage(createCategoryDTO.getImage());

        categoryRepository.save(category);

    }
    @Override
    public void update(Long id, CreateCategoryDTO createCategoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        if (!category.getName().equalsIgnoreCase(createCategoryDTO.getName()) &&
                categoryRepository.existsCategoryByNameIgnoreCase(createCategoryDTO.getName())) {
            throw new AlreadyExistException("Ya existe una categoría con el mismo nombre.");
        }

        if(!createCategoryDTO.getName().equalsIgnoreCase(category.getName())){
            boolean exist = categoryRepository.existsCategoryByNameIgnoreCase(createCategoryDTO.getName());

            if(exist){
                throw new AlreadyExistException("la categoria ya existe con ese nombre");
            }
        }

        category.setName(createCategoryDTO.getName());
        category.setDescription(createCategoryDTO.getDescription());
        category.setImage(createCategoryDTO.getImage());

        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        if (category.getProducts() == null || !category.getProducts().isEmpty()) {
            throw new CategoryHasRelatedProducts("La categoria tiene productos asociados no se puede eliminar");
        }

        categoryRepository.delete(category);
    }
}
