package com.rch.rch_backend.domain.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // 회원가입
    @PostMapping("/users/sign-up")
    public void register(){

    }

    // 로그인
    @PostMapping("/users/sign-in")
    public void login(){

    }

    // 회원 정보 수정
    @PutMapping("/users/{userID}")
    public void fixUserInfo(){

    }

    // 회원 정보 조회
    @GetMapping("/users/{userID}")
    public void getUserInfo(){

    }

    // 회원 탈퇴
    @DeleteMapping("/users/{userID}")
    public void deleteUser(){

    }
}
