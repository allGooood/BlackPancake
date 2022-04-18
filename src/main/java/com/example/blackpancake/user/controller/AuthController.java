package com.example.blackpancake.user.controller;

import com.example.blackpancake.config.role.UserRole;
import com.example.blackpancake.config.jwt.JwtTokenProvider;
import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import com.example.blackpancake.user.exception.PasswordNotMatchException;
import com.example.blackpancake.user.exception.UserNotFoundException;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDTO loginDto){
        Member member = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("등록되지 않은 Email 입니다."));
        if(!loginDto.getPwd().equals(member.getPwd())){
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        return jwtTokenProvider.createToken(member.getEmail()
                                            , member.getAuth().equals("0") ? Collections.singletonList(UserRole.ROLE_MEMBER) : Collections.singletonList(UserRole.ROLE_ADMIN)); //로그인 시 권한부여
    }
}
