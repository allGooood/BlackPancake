package com.example.blackpancake.auth;

import com.example.blackpancake.config.jwt.JwtTokenFilter;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Test
    void 관리자_회원목록_조회() throws Exception {
        //Member adminDto = createAdminDTO();

        mockMvc.perform(get( "/admin/users")
                        .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBlbWFpbC5jb20iLCJyb2xlcyI6WyJBRE" +
                                "1JTiJdLCJpYXQiOjE2NDkxMzM4ODMsImV4cCI6MTY0OTEzNzQ4M30.Vw7PiCwCrD4QzPjrfN-Sg6wvs9LSBqOSTVp012C2aPfzLlwmRb" +
                                "JMihUUqAZNoqXm6MBRewarXt_DwncbKHy3Eg"))
                .andExpect(status().isOk());
    }

    private Member createAdminDTO(){
        Member member = new Member("admin@email.com",
                "리자",
                "관",
                "010-2158-8596",
                "1",
                "관리자네 집",
                "0");
        return member;
    }

}
