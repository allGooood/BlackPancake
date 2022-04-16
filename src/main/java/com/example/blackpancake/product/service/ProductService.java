package com.example.blackpancake.product.service;

import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.dto.AddProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product save(AddProductDTO productDTO) throws Exception;
    Page<Product> findProducts(String category, int pno) throws Exception;
}
