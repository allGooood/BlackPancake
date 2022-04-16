package com.example.blackpancake.user.service;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.dto.JoinDTO;

public interface UserService {
    Member save(JoinDTO joinDTO) throws Exception;
}
