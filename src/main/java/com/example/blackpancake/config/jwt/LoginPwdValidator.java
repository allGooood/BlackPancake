package com.example.blackpancake.config.jwt;

import com.example.blackpancake.config.role.UserRole;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class LoginPwdValidator implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    //UserRepositoryImpl userRepository;

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    public String login(LoginDTO loginDTO){
//        Optional<Member> member = userRepository.findByEmailAndPassword(loginDTO);
//        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다."));
        String pwd = member.getPwd();
        //String auth = member.getAuth();
        String auth = member.getAuth().equals("0") ? "MEMBER" : "ADMIN";

        return User.builder().username(email).password(pwd).roles(auth).build();
    }

}
