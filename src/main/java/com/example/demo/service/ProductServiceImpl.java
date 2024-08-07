package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.respository.CategoryRespository;
import com.example.demo.respository.ProductRepository;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRespository categoryRespository;
    @Override
    public Product createProduct(ProductRequest productRequest) {
        Category category = categoryRespository.findById(productRequest.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(productRequest.getName())
                .pricePerUnit(productRequest.getPricePerUnit())
                .quantity(productRequest.getQuantity())
                .description(productRequest.getDescription())
                .batchNumber(productRequest.getBatchNumber())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(   "Product not found "));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


}
