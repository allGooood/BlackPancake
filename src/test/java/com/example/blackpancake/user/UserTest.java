package com.example.blackpancake.user;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    @Test
    void 회원가입() throws Exception {
        Member member = createMemberDTO();

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member)))
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    void 이미_존재하는_Email_입력시_Exception_발생() throws Exception {
        //given
        Member member = createMemberDTO();
        Member memberDuplicated = createDuplicatedMemberDTO();

        //when
        ResultActions actionNormal = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(MockMvcResultHandlers.print());
        ResultActions actionDuplicated = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDuplicated)))
                .andDo(MockMvcResultHandlers.print());

        //then
        actionNormal.andExpect(status().isCreated());
        actionDuplicated.andExpect(status().isBadRequest());
    }

//    @Test
//    void 로그인() throws Exception {
//        Member member = createMemberDTO();
//        LoginDTO login = createLoginDTO();
//
//        ResultActions memberCreated = mockMvc.perform(post("/user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(member)))
//                        .andDo(MockMvcResultHandlers.print());
//        ResultActions loginResult = mockMvc.perform(post("/user/{email}")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(login)))
//                        .andDo(MockMvcResultHandlers.print());
//
//        memberCreated.andExpect(status().isCreated());
//        loginResult.andExpect(status().isOk());
//    }
//
//    private LoginDTO createLoginDTO() {
//        return null;
//    }

    private Member createMemberDTO(){
        return new Member("email@email.com"
                , "리보"
                , "하"
                , "010-1111-2222"
                , "1"
                ,"Emart24"
                ,"0");
    }

    private Member createDuplicatedMemberDTO(){
        return new Member("email@email.com"
                , "프루"
                , "팁스"
                , "010-6666-0800"
                , "1"
                ,"홈플러스"
                ,"0");
    }
}
