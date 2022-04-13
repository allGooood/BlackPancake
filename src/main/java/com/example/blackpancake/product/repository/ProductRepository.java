package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Optional<Category> category);
}
