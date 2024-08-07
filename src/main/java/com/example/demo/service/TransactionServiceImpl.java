package com.example.demo.service;

import com.example.demo.dto.SellRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import com.example.demo.respository.ProductRepository;
import com.example.demo.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void sellItem(SellRequest sellRequest) {
        Product theProduct = productRepository.findById(sellRequest.getProductId()).orElseThrow(()-> new RuntimeException("Product not found"));

        if(theProduct.getQuantity() <  sellRequest.getQuantitySold()){
            throw new RuntimeException("Insufficient quantity");
        }

        theProduct.setQuantity(theProduct.getQuantity() - sellRequest.getQuantitySold());
        productRepository.save(theProduct);

        Transaction transaction = Transaction.builder()
                .product(theProduct)
                .quantitySold(sellRequest.getQuantitySold())
                .date(LocalDate.now())
                .batchNumber(sellRequest.getBatchNumber())
                .build();

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

    @Override
    public List<Transaction> getTransactionsByProduct(Product product) {
        return transactionRepository.findByProduct(product);
    }

    @Override
    public List<Transaction> getTransactionsByBatchNumber(String batchNumber) {
        return transactionRepository.findByBatchNumber(batchNumber);
    }
}
