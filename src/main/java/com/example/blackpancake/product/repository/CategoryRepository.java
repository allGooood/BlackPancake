package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);
}
