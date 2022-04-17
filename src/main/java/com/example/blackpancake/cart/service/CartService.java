package com.example.blackpancake.cart.service;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.dto.DeleteCartDTO;
import com.example.blackpancake.cart.dto.EditCartDTO;
import org.springframework.stereotype.Service;

public interface CartService {
    Cart save(AddCartDTO cartDTO, String memberEmail);
    Cart patchCartById(EditCartDTO cartDTO, String memberEmail);
    boolean deleteCartById(DeleteCartDTO cartDTO, String memberEmail);
}
