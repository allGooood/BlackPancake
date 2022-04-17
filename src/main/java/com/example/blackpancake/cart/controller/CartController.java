package com.example.blackpancake.cart.controller;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.dto.DeleteCartDTO;
import com.example.blackpancake.cart.dto.EditCartDTO;
import com.example.blackpancake.cart.service.CartService;
import com.example.blackpancake.config.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/member/carts")
    public ResponseEntity<String> save(@RequestHeader("X-AUTH-TOKEN") String jwt
                                        , @RequestBody AddCartDTO cartDTO) throws Exception{
        String memberEmail = jwtTokenProvider.getUserEmail(jwt);
        Cart cart = cartService.save(cartDTO, memberEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품을 카트에 담았습니다.");
    }

    @PatchMapping("/member/carts/{cartId}")
    public ResponseEntity<String> patchCartById(@RequestHeader("X-AUTH-TOKEN") String jwt
                                                , @PathVariable long cartId
                                                , @RequestBody @Valid EditCartDTO cartDTO){
            String memberEmail = jwtTokenProvider.getUserEmail(jwt);
            cartDTO.setCartId(cartId);
            Cart cart = cartService.patchCartById(cartDTO, memberEmail);
        return ResponseEntity.status(HttpStatus.OK).body("장바구니를 수정하였습니다.");
    }

    @DeleteMapping("/member/carts/{cartId}")
    public ResponseEntity<String> deleteCartById(@RequestHeader("X-AUTH-TOKEN") String jwt
                                                , @PathVariable long cartId
                                                , @RequestBody @Valid DeleteCartDTO deleteCartDTO){
        String memberEmail = jwtTokenProvider.getUserEmail(jwt);
        deleteCartDTO.setCartId(cartId);
        boolean cart = cartService.deleteCartById(deleteCartDTO, memberEmail);
        return ResponseEntity.status(HttpStatus.OK).body("해당 상품을 장바구니에서 삭제하였습니다.");
    }
}
