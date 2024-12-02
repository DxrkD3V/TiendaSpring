package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;

import java.util.List;

public interface CategoryRepo {
    Category save(Category category);
    List<Category> findAll();
}