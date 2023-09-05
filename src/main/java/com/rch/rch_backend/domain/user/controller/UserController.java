package com.rch.rch_backend.domain.user.controller;

import com.rch.rch_backend.domain.user.request.SigninDTO;
import com.rch.rch_backend.domain.user.request.SignupDTO;
import com.rch.rch_backend.domain.user.request.UserFixDTO;
import com.rch.rch_backend.domain.user.response.UserInfoDTO;
import com.rch.rch_backend.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(tags = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 일반 회원가입
    @PostMapping("/users/sign-up")
    @ApiOperation(value = "일반 유저 회원가입", notes = "일반 유저용 회원가입을 한다.")
    @ApiImplicitParam(
            name = "username"
            , value = "유저 이메일"
            , required = true
            , dataType = "string"
            , paramType = "query"
    )
    public void register(@Valid @RequestBody SignupDTO signup){
        userService.register(signup);
    }

    // 회사 회원가입
    @PostMapping("/users/companies/sign-up")
    @ApiOperation(value = "회사 유저 회원가입", notes = "회사 유저용 회원가입을 한다.")
    public void companyRegister(@Valid @RequestBody SignupDTO signup){
        userService.companyRegister(signup);
    }

    // 아이디(이메일) 중복체크
    @GetMapping("/users/validate-duplicate")
    @ApiOperation(value = "유저 이메일 중복체크", notes = "이메일 중복체크를 한다.")
    public String validateDuplicate(@RequestParam String email){
        if(userService.validateDuplicate(email)) return "duplicated";
        return "unduplicated";
    }

    // 로그인 (swagger 명세용)
    @PostMapping("/users/sign-in")
    @ApiImplicitParams(
    {
        @ApiImplicitParam(
                name = "username"
                , value = "유저 이메일"
                , required = true
                , dataType = "string"
                , paramType = "query"
        )
        ,
        @ApiImplicitParam(
                name = "password"
                , value = "유저 패스워드"
                , required = true
                , dataType = "string"
                , paramType = "query"
        )
    })
    @ApiOperation(value = "로그인", notes = "로그인을 한다. 이때 request는 form data로 보내야 한다.")
    public void login(SigninDTO signinDTO){
        // 스프링 시큐리티 로그인
    }

    // 로그아웃 (swagger 명세용)
    @GetMapping("/users/sign-out")
    @ApiOperation(value = "로그아웃", notes = "로그아웃을 한다.")
    public void logout(){
        // 스프링 시큐리티 로그인
    }

    // 회원 정보 수정
    @PutMapping("/users/{userID}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다.")
    public void fixUserInfo(@RequestBody UserFixDTO userFixDTO, @PathVariable Long userID){
        userService.fixUserInfo(userFixDTO, userID);
    }

    // Authorize, UserDetails 사용 예시
    @PreAuthorize("hasAnyRole('COMPANYUSER', 'ADMIN')")
    @GetMapping("/foo")
    @ApiOperation(value = "Authorize, UserDetails 사용 예시")
    public String foo(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return "foo";
    }

    // 회원 정보 조회
    @GetMapping("/users/{useremail}")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회한다.")
    public UserInfoDTO getUserInfo(@PathVariable String useremail){
        return userService.getUserInfo(useremail);
    }

    // 회원 탈퇴
    @DeleteMapping("/users/{userID}")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴한다.")
    public void deleteUser(@PathVariable Long userID){
        userService.userquit(userID);
    }
}
