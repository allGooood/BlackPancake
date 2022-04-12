package com.example.blackpancake.product.controller;

import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.service.ProductService;
import com.example.blackpancake.product.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/product")
    public ResponseEntity<String> save(@RequestBody Product product) throws Exception {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("{id}")
                                                .buildAndExpand(product.getId())
                                                .toUri();
        product.setAdd_date(new Date());
        Product result = productService.save(product);
        return ResponseEntity.created(location).body(result.toString());
    }
}
