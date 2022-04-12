package com.example.blackpancake.product.service;

import com.example.blackpancake.product.domain.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category save(Category category) throws Exception;
}
