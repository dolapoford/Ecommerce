package com.example.demo.rest;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product savedProduct = productService.createProduct(productRequest);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating product: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

  //  @PutMapping
//    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest){

//    }


    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable  Long productId){
        Product tempProduct = productService.findById(productId);
        if(tempProduct == null){
            throw new RuntimeException("Product not found");
        }
        productService.deleteById(productId);

        return  " Delete Product Id -  " + productId;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
