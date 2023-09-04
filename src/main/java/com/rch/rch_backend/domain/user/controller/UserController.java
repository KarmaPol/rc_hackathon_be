package com.rch.rch_backend.domain.user.controller;

import com.rch.rch_backend.domain.user.request.SignupDTO;
import com.rch.rch_backend.domain.user.request.UserFixDTO;
import com.rch.rch_backend.domain.user.response.UserInfoDTO;
import com.rch.rch_backend.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 일반 회원가입
    @PostMapping("/users/sign-up")
    @ApiOperation(value = "일반 유저 회원가입", notes = "일반 유저용 회원가입을 한다.")
    public void register(@RequestBody SignupDTO signup){
        userService.register(signup);
    }

    // 회사 회원가입
    @PostMapping("/users/companies/sign-up")
    @ApiOperation(value = "회사 유저 회원가입", notes = "회사 유저용 회원가입을 한다.")
    public void companyRegister(@RequestBody SignupDTO signup){
        userService.companyRegister(signup);
    }

    // 로그인
    @PostMapping("/users/sign-in")
    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    public void login(){
        // todo 스프링 시큐리티 로그인
    }

    // 회원 정보 수정
    @PutMapping("/users/{userID}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다.")
    public void fixUserInfo(@RequestBody UserFixDTO userFixDTO, @PathVariable Long userID){
        userService.fixUserInfo(userFixDTO, userID);
    }

    // 회원 정보 조회
    @GetMapping("/users/{userID}")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회한다.")
    public UserInfoDTO getUserInfo(@PathVariable Long userID){
        return userService.getUserInfo(userID);
    }

    // 회원 탈퇴
    @DeleteMapping("/users/{userID}")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴한다.")
    public void deleteUser(@PathVariable Long userID){
        userService.userquit(userID);
    }
}
