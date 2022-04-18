package com.example.blackpancake.product.controller;

import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.dto.AddProductDTO;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.product.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductServiceImpl productService, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/admin/product")
    public ResponseEntity<String> save(@RequestBody AddProductDTO product) throws Exception {
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                                .path("{id}")
//                                                .buildAndExpand(product)
//                                                .toUri();
        //product.setAdd_date(new Date());
        Product result = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toString());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listUpProducts(@RequestParam(value="category", required=false)String code, @RequestParam(value="page", defaultValue = "1")int pno) throws Exception {
        List<Product> productList = productService.findProducts(code, pno).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


}
