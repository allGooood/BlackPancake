package com.example.blackpancake.product.repository;

import com.example.blackpancake.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
