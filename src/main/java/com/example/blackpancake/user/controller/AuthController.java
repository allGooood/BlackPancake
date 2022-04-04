package com.example.blackpancake.user.controller;

import com.example.blackpancake.config.UserRole;
import com.example.blackpancake.config.jwt.JwtTokenProvider;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import com.example.blackpancake.user.exception.UserNotFoundException;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDTO loginDto){
        Member member = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("등록되지 않은 Email 입니다."));
        return jwtTokenProvider.createToken(member.getEmail(), Collections.singletonList(UserRole.USER));
    }
}
