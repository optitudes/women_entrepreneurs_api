package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Category;
import co.edu.uniquindio.women_entrepeneurs_api.repo.CategoryRepo;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(Category c) throws Exception {
        return categoryRepo.save(c);
    }

    @Override
    public Category updateCategory(Category c) throws Exception {
        return categoryRepo.save(c);
    }

    @Override
    public void deleteCategory(int id) throws Exception {
        categoryRepo.deleteById(id);
    }
}
