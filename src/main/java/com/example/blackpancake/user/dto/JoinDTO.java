package com.example.blackpancake.user.dto;

import com.example.blackpancake.user.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Inherited;

@Data
@NoArgsConstructor
public class JoinDTO {
    private String email;
    private String first_name;
    private String last_name;
    private String mobile;
    private String pwd;
    private String address;
    private String auth;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .first_name(first_name)
                .last_name(last_name)
                .mobile(mobile)
                .pwd(pwd)
                .address(address)
                .auth(auth)
                .build();
    }
}
