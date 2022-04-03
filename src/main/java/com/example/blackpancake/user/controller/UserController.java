package com.example.blackpancake.user.controller;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import com.example.blackpancake.user.repository.UserRepository;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<String> join(@RequestBody Member member){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("{id}")
                                                .buildAndExpand(member.getId())
                                                .toUri();
        Member savedUser = userService.join(member);
        return ResponseEntity.created(location).body("회원가입이 완료 되었습니다.");
        //return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("{email}")
                                                .buildAndExpand(loginDto.getEmail())
                                                .toUri();
        return null;
    }

//    @GetMapping("/user/{email}")
//    public EntityModel<Optional<Member>> retrieveUser(@PathVariable String email){
//        Optional<Member> member = userService.findByEmail(email);
//
//    }
}


//        Member savedUser = userRepository.join(member);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                                    .path("{id}")
//                                                    .buildAndExpand(savedUser.getId())
//                                                    .toUri();
//        return ResponseEntity.created(location).build();
