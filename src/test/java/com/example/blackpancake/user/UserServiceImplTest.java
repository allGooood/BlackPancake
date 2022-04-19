package com.example.blackpancake.user;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.JoinDTO;
import com.example.blackpancake.user.repository.UserRepository;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {UserRepository.class, UserServiceImpl.class})
@ActiveProfiles("test")
public class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Test
    void save(){
        Member member = createMemberDTO().toEntity();
        Mockito.when(userRepository.save(member)).thenReturn(member);

        Member joinedMember = userService.save(createMemberDTO());

        Assertions.assertEquals(joinedMember.getEmail(), member.getEmail());
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
