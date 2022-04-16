package com.example.blackpancake.cart.service;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.repository.CartRepository;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart save(AddCartDTO cartDTO, String memberEmail){
        Optional<Member> member = userRepository.findByEmail(memberEmail);
        Optional<Product> product = productRepository.findById(cartDTO.getProductId());
        Cart cart = cartDTO.toEntity(member.get(), product.get());
        return cartRepository.save(cart);
    }

}
