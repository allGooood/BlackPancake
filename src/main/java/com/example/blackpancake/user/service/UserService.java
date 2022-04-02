package com.example.blackpancake.user.service;

import com.example.blackpancake.user.domain.Member;

public interface UserService {
    Member join(Member member) throws Exception;
    //Member retrieveUser(String email) throws Exception;
}
