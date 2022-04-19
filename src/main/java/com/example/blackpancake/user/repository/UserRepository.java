package com.example.blackpancake.user.repository;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
