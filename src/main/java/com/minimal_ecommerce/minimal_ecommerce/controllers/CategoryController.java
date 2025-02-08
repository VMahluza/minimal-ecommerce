package com.minimal_ecommerce.minimal_ecommerce.controllers;

import com.minimal_ecommerce.minimal_ecommerce.models.Category;
import com.minimal_ecommerce.minimal_ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long Id) {
        return categoryService.getCategoryById(Id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long Id,@RequestBody Category category){
        try{
            Category updatedCategory = categoryService.updateCategory(Id, category);
            return ResponseEntity.ok(updatedCategory);

        } catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long Id){
        categoryService.deleteCategory(Id);
        return ResponseEntity.noContent().build();
    }
}
