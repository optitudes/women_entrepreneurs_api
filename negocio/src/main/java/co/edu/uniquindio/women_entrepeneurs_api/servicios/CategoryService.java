package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Category;

public interface CategoryService {
    Category createCategory(Category c) throws Exception;
    Category updateCategory(Category c) throws Exception;

    void deleteCategory(int id) throws Exception;
}
