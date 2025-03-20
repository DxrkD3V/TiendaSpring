package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.categories;


import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CreateCategoryDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.AlreadyExistException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.categories.CategoryRepository;
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
    public void create(CreateCategoryDTO createProductDTO){
        if (categoryRepository.existsProductByNameIgnoreCase(createProductDTO.getName())) {
           throw new AlreadyExistException(String.format("Ya existe un producto con el nombre %s", createProductDTO.getName()));
        }
        Category category = new Category();
        category.setName(createProductDTO.getName());
        category.setDescription(createProductDTO.getDescription());
        category.setImage(createProductDTO.getImage());

        Category savedCategory = categoryRepository.save(category);

    }
}
