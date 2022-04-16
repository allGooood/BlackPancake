package com.example.blackpancake.product.controller;

import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/admin/product")
    public ResponseEntity<String> save(@RequestBody Product product) throws Exception {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("{id}")
                                                .buildAndExpand(product.getId())
                                                .toUri();
        product.setAdd_date(new Date());
        Product result = productService.save(product);
        return ResponseEntity.created(location).body(result.toString());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listUpProducts(@RequestParam(value="category", required=false)String code, @RequestParam(value="page", defaultValue = "1")int pno) throws Exception {
        List<Product> productList = productService.findProducts(code, pno).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> findByCategory(@RequestParam String category){
//        System.out.println("category: "  + category);
//        List<Product> productList = productRepository.findByCategory(new Category(category, ""));
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//    }
}
