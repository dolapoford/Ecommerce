package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    Product findById(Long id);

    void deleteById(Long id);
}
