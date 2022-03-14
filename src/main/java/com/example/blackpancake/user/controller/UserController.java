package com.example.blackpancake.user.controller;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.JoinForm;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Transactional
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<Member> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public ResponseEntity<Member> createUser(@RequestBody Member member){
        Member savedUser = userRepository.join(member);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                    .path("{id}")
                                                    .buildAndExpand(savedUser.getId())
                                                    .toUri();
        return ResponseEntity.created(location).build();
        //return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
}
