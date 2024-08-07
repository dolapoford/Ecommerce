package com.example.demo.respository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository <Category,Long> {

    Boolean existsByName(String name);
}
