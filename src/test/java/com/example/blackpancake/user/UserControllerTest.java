package com.example.blackpancake.user;

import com.example.blackpancake.user.dto.JoinDTO;
import com.example.blackpancake.user.exception.EmailAlreadyExistsException;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    @Test
    @DisplayName("회원가입")
    public void join() throws Exception {
        JoinDTO joinDTO = createMemberDTO();
        given(userService.save(joinDTO)).willReturn(joinDTO.toEntity());

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("이미 존재하는 Email입력시 예외발생")
    void joinException() throws Exception {
        JoinDTO duplicateDTO = createDuplicatedMemberDTO();
        given(userService.save(duplicateDTO)).willThrow(new EmailAlreadyExistsException("이미 존재하는 Email입니다."));

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    private JoinDTO createMemberDTO(){
        return new JoinDTO("email@email.com"
                , "리보"
                , "하"
                , "010-1111-2222"
                , "1"
                ,"Emart24"
                ,"0");
    }

    private JoinDTO createDuplicatedMemberDTO(){
        return new JoinDTO("email@email.com"
                , "프루"
                , "팁스"
                , "010-6666-0800"
                , "1"
                ,"홈플러스"
                ,"0");
    }
}
