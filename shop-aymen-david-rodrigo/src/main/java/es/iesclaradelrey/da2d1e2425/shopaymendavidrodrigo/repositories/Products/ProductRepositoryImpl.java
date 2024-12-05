package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Products;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.Categories.CategoryRepo;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.base.RepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Category, Long> implements ProductRepo {
}
