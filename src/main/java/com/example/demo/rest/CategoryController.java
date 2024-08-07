package com.example.demo.rest;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.Category;
import com.example.demo.respository.CategoryRespository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRespository categoryRespository;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryDTO) {
        if(categoryRespository.existsByName(categoryDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category already exists.");

        }
        try {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            Category theCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(theCategory, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Error creating category: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
   public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        Category theCategory = categoryService.findById(categoryId);

        if (theCategory == null) {
            throw new RuntimeException("Category Not Found");
        }
        return "Deleted Category of Id - " + categoryId;
    }

}
