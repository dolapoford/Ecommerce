package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.respository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRespository categoryRespository ;

    @Override
    public Category createCategory(Category category) {

        Category newCategory = Category.builder()
                .name(category.getName())
                .build();
        return categoryRespository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRespository.findAll();
    }

    @Override
    public void deleteById(Long categoryId) {
            categoryRespository.deleteById(categoryId);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRespository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Id not found"));
    }
}
