package com.example.blackpancake.product.service.impl;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) throws Exception {
        return categoryRepository.save(category);
    }
}
