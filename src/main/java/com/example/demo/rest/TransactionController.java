package com.example.demo.rest;

import com.example.demo.dto.SellRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import com.example.demo.respository.TransactionRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
   private TransactionService transactionService;

    @Autowired
    private ProductService productService;

    @PostMapping("/sell")
    public ResponseEntity<String> sellItem(@RequestBody SellRequest sellRequest) {
        try {
            transactionService.sellItem(sellRequest);
            return ResponseEntity.ok("Item sold successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/byDate")
    public List<Transaction> getTransactionsByDate(@RequestParam LocalDate date) {
        return transactionService.getTransactionsByDate(date);
    }

    @GetMapping("/byProduct")
    public List<Transaction> getTransactionsByProduct(@RequestParam Long productId) {
        Product product = productService.findById(productId);
        return transactionService.getTransactionsByProduct(product);
    }

    @GetMapping("/byBatchNumber")
    public List<Transaction> getTransactionsByBatchNumber(@RequestParam String batchNumber) {
        return transactionService.getTransactionsByBatchNumber(batchNumber);
    }

}
