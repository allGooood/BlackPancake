package com.example.blackpancake.auth;

import com.example.blackpancake.config.UserRole;
import com.example.blackpancake.config.jwt.JwtTokenProvider;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.impl.UserRepositoryImpl;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@CrossOrigin
public class AuthController {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth")
    public String auth(@RequestBody String email){
        Member member = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다."));
        return jwtTokenProvider.createToken(member.getEmail(), Collections.singletonList(UserRole.USER));
    }
}
