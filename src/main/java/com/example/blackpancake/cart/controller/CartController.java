package com.example.blackpancake.cart.controller;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.service.CartService;
import com.example.blackpancake.config.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/user/cart")
    public ResponseEntity<String> save(@RequestHeader("X-AUTH-TOKEN") String jwt, @RequestBody AddCartDTO cartDTO) throws Exception{
        String memberEmail = jwtTokenProvider.getUserId(jwt);
        System.out.println("memberEmail: " + memberEmail);
        Cart cart = cartService.save(cartDTO, memberEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품을 카트에 담았습니다.");
    }
}
