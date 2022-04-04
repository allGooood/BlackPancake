package com.example.blackpancake.user.repository;

import com.example.blackpancake.user.domain.Member;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    Member join(Member member);
    Optional<Member> findByEmail(String email);
    //Optional<Member> findByEmailAndPassword(LoginDTO loginDTO);

    List<Member> findAll();
    Member login(Member member);
}
