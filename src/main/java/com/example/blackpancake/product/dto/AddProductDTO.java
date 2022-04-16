package com.example.blackpancake.product.dto;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductDTO {
    private String name;
    private int price;
    private int stock;
    private String description;
    private String categoryCode;

    public Product toEntity(Category category){
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .description(description)
                .category(category)
                .build();
    }
}
