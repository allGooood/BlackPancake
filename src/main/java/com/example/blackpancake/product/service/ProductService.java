package com.example.blackpancake.product.service;

import com.example.blackpancake.product.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product save(Product product) throws Exception;
    Page<Product> findProducts(String category, int pno) throws Exception;
}
