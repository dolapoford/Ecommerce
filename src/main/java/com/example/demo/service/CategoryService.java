package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    void deleteById(Long categoryId);

    Category findById(Long categoryId);
}
