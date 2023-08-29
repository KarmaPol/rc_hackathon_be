package com.rch.rch_backend.domain.user.service;

import com.rch.rch_backend.domain.user.model.CompanyUser;
import com.rch.rch_backend.domain.user.model.NormalUser;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import com.rch.rch_backend.domain.user.request.SignupDTO;
import com.rch.rch_backend.domain.user.request.UserFixDTO;
import com.rch.rch_backend.domain.user.response.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void register(SignupDTO signup) {
        NormalUser signupUser = NormalUser.builder().name(signup.getName()).email(signup.getEmail()).password(signup.getPassword()).phoneNumber(signup.getPhoneNumber()).build();

        userRepository.save(signupUser);
    }

    public void companyRegister(SignupDTO signup) {
        CompanyUser signupUser = CompanyUser.builder().name(signup.getName()).email(signup.getEmail()).password(signup.getPassword()).phoneNumber(signup.getPhoneNumber()).build();

        userRepository.save(signupUser);
    }

    public void fixUserInfo(UserFixDTO userFixDTO, Long userID) {
        Users findUser = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저"));

        findUser.changeUserInfo(userFixDTO.getName(),userFixDTO.getEmail(),userFixDTO.getPassword(), userFixDTO.getPhoneNumber());
    }

    public UserInfoDTO getUserInfo(Long userID) {
        Users findUser = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저"));

        UserInfoDTO userInfo = UserInfoDTO.builder().name(findUser.getName()).email(findUser.getEmail()).password(findUser.getPassword()).phoneNumber(findUser.getPhoneNumber()).build();

        return userInfo;
    }

    public void userquit(Long userID) {
        Users findUser = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저"));

        findUser.setStatus("회원 탈퇴");
    }
}
