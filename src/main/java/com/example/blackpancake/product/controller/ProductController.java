package com.example.blackpancake.product.controller;

import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.dto.AddProductDTO;
import com.example.blackpancake.product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/product")
    public ResponseEntity<String> save(@RequestBody AddProductDTO product) throws Exception {
        Product result = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toString());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listUpProducts(@RequestParam(value="category", required=false)String code, @RequestParam(value="page", defaultValue = "1")int pno) throws Exception {
        List<Product> productList = productService.findProducts(code, pno).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


}
