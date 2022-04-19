package com.example.blackpancake.user;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void findTest(){
        //JpaRepository
        List<Member> membersFound = userRepository.findAll();
        for(Member member : membersFound){
            System.out.println(member.toString());
        }

        Optional<Member> entitiesFound = userRepository.findByEmail("test@email.com");
        System.out.println(entitiesFound);
    }

}
