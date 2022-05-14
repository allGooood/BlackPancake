package com.example.blackpancake.cart.service;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.dto.DeleteCartDTO;
import com.example.blackpancake.cart.dto.EditCartDTO;
import com.example.blackpancake.order.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    Cart save(AddCartDTO cartDTO, String memberEmail);
    Cart patchCartById(EditCartDTO cartDTO, String memberEmail);
    boolean deleteCartById(DeleteCartDTO cartDTO, String memberEmail);
    Page<Cart> findAllByMemberId(String email, int pno);
}
