package com.example.blackpancake.user.controller;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.JoinDTO;
import com.example.blackpancake.user.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //    @PostMapping("/user")
//    public ResponseEntity<String> join(@RequestBody Member joinDTO){
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                                .path("{id}")
//                                                .buildAndExpand(joinDTO.getId())
//                                                .toUri();
//        Member savedUser = userService.save(joinDTO);
//        return ResponseEntity.created(location).body("회원가입이 완료 되었습니다.");
//        //return ResponseEntity.status(HttpStatus.OK).body(savedUser);
//        //return ResponseEntity.created(location).build();
//    }

    @PostMapping("/user")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinDTO){
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("{email}")
//                .buildAndExpand(joinDTO.getEmail())
//                .toUri();

        Member savedUser = userService.save(joinDTO);
        //return ResponseEntity.created(location).body("회원가입이 완료 되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser.toString());
        //return ResponseEntity.created(location).build();
    }

}

