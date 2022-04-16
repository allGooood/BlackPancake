package com.example.blackpancake.user.service.impl;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.JoinDTO;
import com.example.blackpancake.user.exception.EmailAlreadyExistsException;
import com.example.blackpancake.user.exception.UserNotFoundException;
import com.example.blackpancake.user.repository.UserRepository;
import com.example.blackpancake.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Member save(Member joinDTO){
//        if(userRepository.findByEmail(joinDTO.getEmail()).isPresent()){
//            throw new EmailAlreadyExistsException("이미 존재하는 Email 입니다.");
//        }
//        Member savedUser = userRepository.save(joinDTO);
//        return savedUser;
//    }

    @Override
    public Member save(JoinDTO joinDTO){
        if(userRepository.findByEmail(joinDTO.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("이미 존재하는 Email 입니다.");
        }
        Member member = joinDTO.toEntity();
        Member savedUser = userRepository.save(member);
        return savedUser;
    }
}
