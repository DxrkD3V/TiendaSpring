package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.base.RepositoryImpl;
import org.springframework.stereotype.Repository;

//CategoriaImplementada
@Repository
public class CategoryRepositoryImpl extends RepositoryImpl<Category, Long> implements CategoryRepo {

}

