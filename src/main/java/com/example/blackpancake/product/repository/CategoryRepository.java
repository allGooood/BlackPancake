package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
