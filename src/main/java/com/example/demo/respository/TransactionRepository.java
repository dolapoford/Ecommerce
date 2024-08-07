package com.example.demo.respository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByDate(LocalDate date);
    List<Transaction> findByBatchNumber(String batchNumber);

    List <Transaction> findByProduct(Product product);
}
