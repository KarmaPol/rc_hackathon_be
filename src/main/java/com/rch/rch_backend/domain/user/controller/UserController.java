package com.rch.rch_backend.domain.user.controller;

import com.rch.rch_backend.domain.user.request.SignupDTO;
import com.rch.rch_backend.domain.user.request.UserFixDTO;
import com.rch.rch_backend.domain.user.response.UserInfoDTO;
import com.rch.rch_backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 일반 회원가입
    @PostMapping("/users/sign-up")
    public void register(@RequestBody SignupDTO signup){
        userService.register(signup);
    }

    // 회사 회원가입
    @PostMapping("/users/sign-up/company")
    public void companyRegister(@RequestBody SignupDTO signup){
        userService.companyRegister(signup);
    }

    // 로그인
    @PostMapping("/users/sign-in")
    public void login(){
        // todo 스프링 시큐리티 로그인
    }

    // 회원 정보 수정
    @PutMapping("/users/{userID}")
    public void fixUserInfo(@RequestBody UserFixDTO userFixDTO, @PathVariable Long userID){
        userService.fixUserInfo(userFixDTO, userID);
    }

    // 회원 정보 조회
    @GetMapping("/users/{userID}")
    public UserInfoDTO getUserInfo(@PathVariable Long userID){
        return userService.getUserInfo(userID);
    }

    // 회원 탈퇴
    @DeleteMapping("/users/{userID}")
    public void deleteUser(@PathVariable Long userID){
        userService.userquit(userID);
    }
}
