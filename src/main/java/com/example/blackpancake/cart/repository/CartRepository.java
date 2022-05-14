package com.example.blackpancake.cart.repository;

import com.example.blackpancake.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(long memberId);
    Page<Cart> findAllByMemberId(long memberId, Pageable paging);
}
