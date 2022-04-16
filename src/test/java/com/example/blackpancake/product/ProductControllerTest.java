package com.example.blackpancake.product;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;

    @Test
    void 조인(){
        //given
        Category category = new Category();
        category.setCode("200");
        category.setName("Phone Accessories");
        Product product = new Product("testItem", 10000, 5, "description", new Date(), category);

        categoryRepository.save(category);
        productRepository.save(product);

        //when
        List<Product> productList = productRepository.findAll();

        //then
        productList.forEach(Product -> {
            System.out.println(Product);
            System.out.println(Product.getCategory());
        });
    }

    @Test
    void 상품목록조회_그리고_카테고리_선택시_해당하는_상품만_조회() throws Exception{
        mockMvc.perform(get("/products?category=200")).andExpect(status().isOk());
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 상품목록_페이징() throws Exception {
        mockMvc.perform(get("/products?page=1")).andExpect(status().isOk());
        mockMvc.perform(get("/products?page=2")).andExpect(status().isOk());
        mockMvc.perform(get("/products?page=1&category=200")).andExpect(status().isOk());
    }

    @Test
    void 관리자만_상품등록_가능() throws Exception {
        Product product = createProductDTO();

        mockMvc.perform(post("/admin/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isForbidden());
    }


    Product createProductDTO(){
        return new Product("OIOI 폰케이스"
                , 34000
                , 50
                , "버건디 색상입니다."
                , new Date()
                , new Category("100", "Phone Cases"));
    }

}
