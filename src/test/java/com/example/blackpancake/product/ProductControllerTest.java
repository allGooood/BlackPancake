package com.example.blackpancake.product;

import com.example.blackpancake.product.domain.Category;
import com.example.blackpancake.product.domain.Product;
import com.example.blackpancake.product.repository.CategoryRepository;
import com.example.blackpancake.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    CategoryRepository categoryRepository;

    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;

    @Test
    void 조인(){
//        //given
//        Category category = new Category();
//        category.setCode("200");
//        category.setName("Phone Accessories");
//        Product product = new Product("testItem", 10000, 5, "description", new Date(), category);
//
//        categoryRepository.save(category);
//        productRepository.save(product);
//
//        //when
//        List<Product> productList = productRepository.findAll();
//        productList.forEach(Product -> {
//            System.out.println(Product);
//            System.out.println(Product.getCategory());
//        });

        Category category = new Category();
        //category

        Product product = new Product();
    }

}
