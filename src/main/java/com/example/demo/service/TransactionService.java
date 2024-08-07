package com.example.demo.service;

import com.example.demo.dto.SellRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    void sellItem(SellRequest sellRequest);

    List<Transaction> getTransactionsByDate(LocalDate date);
    List<Transaction> getTransactionsByProduct(Product product);
    List<Transaction> getTransactionsByBatchNumber(String batchNumber);
}
