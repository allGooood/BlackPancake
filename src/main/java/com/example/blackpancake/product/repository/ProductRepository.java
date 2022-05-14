package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
