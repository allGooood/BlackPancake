package com.example.blackpancake.product.service.impl;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) throws Exception {
        //예외처리 추가 --> if 관리자가 아니라면
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findProducts(String categoryCode, int pno) throws Exception {
        Page<Product> productList;
        Pageable paging = PageRequest.of(pno-1, 2, Sort.Direction.DESC, "id");
        if(categoryCode != null){
            Optional<Category> category = categoryRepository.findByCode(categoryCode);
            productList = productRepository.findAllByCategory(category, paging);
        }else{
            productList = productRepository.findAll(paging);
        }
        return productList;
    }


}
