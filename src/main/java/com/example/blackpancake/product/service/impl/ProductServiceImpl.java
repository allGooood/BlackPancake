package com.example.blackpancake.product.service.impl;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.dto.AddProductDTO;
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
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product save(AddProductDTO productDTO) throws Exception {
        //예외처리 추가 -->
        //관리자가 아니라면
        //카테고리가 없을때
        //상품명이 존재할때
        //예외처리<--

        Optional<Category> category = categoryRepository.findByCode(productDTO.getCategoryCode());
        Product product = productDTO.toEntity(category.get());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findProducts(String categoryCode, int pno) throws Exception {
        //예외처리 추가 -->
        //최대 페이지를 초과한 검색

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
