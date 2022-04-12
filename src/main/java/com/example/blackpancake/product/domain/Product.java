package com.example.blackpancake.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stock;
    private String description;
    private Date add_date;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;

    public Product(String name, int price, int stock, String description, Date add_date, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.add_date = add_date;
        this.category = category;
    }
}
