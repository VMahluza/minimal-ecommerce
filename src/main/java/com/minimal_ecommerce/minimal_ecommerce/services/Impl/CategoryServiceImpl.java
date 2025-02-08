package com.minimal_ecommerce.minimal_ecommerce.services.Impl;

import com.minimal_ecommerce.minimal_ecommerce.models.Category;
import com.minimal_ecommerce.minimal_ecommerce.repositories.CategoryRepository;
import com.minimal_ecommerce.minimal_ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Optional<Category> getCategoryById(Long Id) {
        return categoryRepository.findById(Id);
    }
    @Override
    public Category updateCategory(Long Id, Category updatedCategory) {
        /*
         * Get the user by id using findById
         * since the findById returns a user
         * set the user returned to the updated user
         * then use the repository to save the user
         * if then the user is not found you must make an exception
         * */
        return categoryRepository.findById(Id).map(category-> {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());

            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category with this id is not found"));
    }
    @Override
    public void deleteCategory(Long Id) {
        categoryRepository.deleteById(Id);
    }
}
