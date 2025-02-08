package com.minimal_ecommerce.minimal_ecommerce.services;
import com.minimal_ecommerce.minimal_ecommerce.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long Id);
    Category updateCategory(Long Id, Category updatedCategory);
    void deleteCategory(Long Id);
}
