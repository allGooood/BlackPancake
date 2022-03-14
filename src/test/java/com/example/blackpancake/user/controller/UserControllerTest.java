package com.example.blackpancake.user.controller;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class UserControllerTest {
    @Autowired
    UserRepository userRepository;

//    @Test
//    void 회원가입(){
//        //given
//        Member member = new Member("ming920331@gmail.com"
//                                    , "민경"
//                                    , "김"
//                                    , "01087269203"
//                                    , "1"
//                                    ,"김민경주소"
//                                    ,"1");
//        //when
//        userRepository.join(member);
//        Member joinedMember = userRepository.findByEmail(member.getEmail()).get();
//        //then
//        //Member findMember = userRepository.findOne(member.getEmail()).get();
//        assertThat(member).isEqualTo(joinedMember);
//    }

//    @Test
//    void Email로_회원검색(){
//        //given
//        String email = "ming920331@gmail.com";
//        //when
//        Optional<Member> memberFound = userRepository.findByEmail(email);
//        //then
//        assertThat(email).isEqualTo(memberFound.get().getEmail());
//    }
}
