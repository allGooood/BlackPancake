package com.example.blackpancake.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


public class Product {
    private Long id;

    private String name;
    private int price;
    private int stock;
    private String desc;
    private Date add_date;
    private String category_code;
}
