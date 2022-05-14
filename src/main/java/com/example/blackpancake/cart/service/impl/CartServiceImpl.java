package com.example.blackpancake.cart.service.impl;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.dto.DeleteCartDTO;
import com.example.blackpancake.cart.dto.EditCartDTO;
import com.example.blackpancake.cart.repository.CartRepository;
import com.example.blackpancake.cart.service.CartService;
import com.example.blackpancake.exception.NoSuchItemException;
import com.example.blackpancake.exception.UserNotMatchedException;
import com.example.blackpancake.order.domain.Orders;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.ProductRepository;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart save(AddCartDTO cartDTO, String memberEmail){
        // +예외처리 : 카트에 이미 존재하는 물건 --> patchCartById 실행

        Optional<Member> member = userRepository.findByEmail(memberEmail);
        Optional<Product> product = productRepository.findById(cartDTO.getProductId());
        Cart cart = cartDTO.toEntity(member.get(), product.get());
        return cartRepository.save(cart);
    }

    @Override
    public Cart patchCartById(EditCartDTO cartDTO, String memberEmail) {
        long memberId = userRepository.findByEmail(memberEmail).get().getId();
        long memberIdCart = cartRepository.findById(cartDTO.getCartId()).get().getMember().getId();
        if(memberId != memberIdCart){
            throw new UserNotMatchedException("유저 정보가 일치하지 않습니다.");
        }

        Optional<Cart> cart = cartRepository.findById(cartDTO.getCartId());
        if(!cart.isPresent()){
            throw new NoSuchItemException("해당 상품이 카트에 존재하지 않습니다.");
        }
        cart.get().setAmount(cartDTO.getAmount());
        return cartRepository.save(cart.get());
    }

    @Override
    public boolean deleteCartById(DeleteCartDTO cartDTO, String memberEmail) {
        long memberId = userRepository.findByEmail(memberEmail).get().getId();
        long memberIdCart = cartRepository.findById(cartDTO.getCartId()).get().getMember().getId();
        if(memberId != memberIdCart){
            throw new UserNotMatchedException("유저 정보가 일치하지 않습니다.");
        }

        Optional<Cart> cart = cartRepository.findById(cartDTO.getCartId());
        if(cart.isPresent()){
            cartRepository.deleteById(cartDTO.getCartId());
            return true;
        }else{
            throw new NoSuchItemException("해당 상품이 카트에 존재하지 않습니다.");
        }
    }

    @Override
    public Page<Cart> findAllByMemberId(String email, int pno) {
        long memberId = userRepository.findByEmail(email).get().getId();

        Pageable paging = PageRequest.of(pno-1, 2, Sort.Direction.DESC, "id");
        Page<Cart> cartList = cartRepository.findAllByMemberId(memberId, paging);
        return cartList;
    }

}
