package com.example.blackpancake.product.service.impl;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) throws Exception {
        //예외처리 추가 --> if 관리자가 아니라면
        return productRepository.save(product);
    }


}
