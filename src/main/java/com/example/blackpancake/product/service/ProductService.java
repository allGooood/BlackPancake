package com.example.blackpancake.product.service;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product save(Product product) throws Exception;
    //List<Product> listUpProducts() throws Exception;
}
