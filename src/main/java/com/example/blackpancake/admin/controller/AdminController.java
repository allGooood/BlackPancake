package com.example.blackpancake.admin.controller;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import com.example.blackpancake.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Member>> findAll(){
        List<Member> memberList = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(memberList);
    }

}
