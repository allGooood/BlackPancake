package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);

}
