package com.example.blackpancake.cart.dto;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.user.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Optional;

@Data
@NoArgsConstructor
public class AddCartDTO {
    @Min(0)
    private int amount;
    private Long productId;

    public Cart toEntity(Member member, Product product){
        return Cart.builder()
                .amount(amount)
                .member(member)
                .product(product)
                .build();
    }

}
