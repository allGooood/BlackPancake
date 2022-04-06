package com.example.blackpancake.admin.repository;

import com.example.blackpancake.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Member, Long> {
}
