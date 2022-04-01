package com.example.blackpancake.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String first_name;
    private String last_name;
    private String mobile;
    private String pwd;
    private String address;
    private String auth;

    public Member(String email, String first_name, String last_name, String mobile, String pwd, String address, String auth) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
        this.pwd = pwd;
        this.address = address;
        this.auth = auth;
    }
}
