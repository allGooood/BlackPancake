package com.example.blackpancake.auth;

import com.example.blackpancake.config.jwt.JwtTokenProvider;
import com.example.blackpancake.config.role.UserRole;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void 관리자_회원목록_조회() throws Exception {
        Optional<Member> admin = createAdminDTO();
        String token = createToken(admin.get());

        mockMvc.perform(get( "/admin/users")
                        .header("X-AUTH-TOKEN", token))
                .andExpect(status().isOk());
    }

    @Test
    void 관리자_권한이_없으면_목록조회_실패() throws Exception{
        Optional<Member> admin = createAdminDTO();
        Optional<Member> member = createMemberDTO();

        String tokenAdmin = createToken(admin.get());
        String tokenMember = createToken(member.get());

        mockMvc.perform(get( "/admin/users")
                        .header("X-AUTH-TOKEN", tokenAdmin))
                .andExpect(status().isOk());
        mockMvc.perform(get( "/admin/users")
                        .header("X-AUTH-TOKEN", tokenMember))
                .andExpect(status().isForbidden());
    }

    private Optional<Member> createAdminDTO(){
        return userRepository.findByEmail("admin@email.com");
    }

    private Optional<Member> createMemberDTO(){
        return userRepository.findByEmail("test@email.com");
    }

    private String createToken(Member userDTO){
        return jwtTokenProvider.createToken(userDTO.getEmail()
                , userDTO.getAuth().equals("0")
                        ? Collections.singletonList(UserRole.ROLE_MEMBER)
                        : Collections.singletonList(UserRole.ROLE_ADMIN));
    }

}
