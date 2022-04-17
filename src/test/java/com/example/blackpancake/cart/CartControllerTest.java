package com.example.blackpancake.cart;

import com.example.blackpancake.cart.domain.Cart;
import com.example.blackpancake.cart.dto.AddCartDTO;
import com.example.blackpancake.cart.service.impl.CartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CartServiceImpl cartService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 조인() throws Exception{
        AddCartDTO cartDTO = new AddCartDTO();
        cartDTO.setAmount(1);
        cartDTO.setProductId(4L);

        //given
        given(cartService.save(cartDTO, "test@email.com")).willReturn(new Cart());

        //when
        ResultActions resultActions = mockMvc.perform(post("/member/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDTO)));

        //then
        resultActions.andExpect(status().isCreated())
                .andDo(print());
    }
}
