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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(SignupDTO signup) {
        String normalPw = signup.getPassword();
        String encodedPw = passwordEncoder.encode(normalPw);

        if(validateDuplicate(signup.getEmail()))
            throw new UsernameNotFoundException("이미 가입된 이메일입니다.");

        NormalUser signupUser = NormalUser.builder().name(signup.getName()).email(signup.getEmail()).password(encodedPw).phoneNumber(signup.getPhoneNumber()).build();

        userRepository.save(signupUser);
    }

    public void companyRegister(SignupDTO signup) {
        String normalPw = signup.getPassword();
        String encodedPw = passwordEncoder.encode(normalPw);

        if(validateDuplicate(signup.getEmail()))
            throw new UsernameNotFoundException("이미 가입된 이메일입니다.");

        CompanyUser signupUser = CompanyUser.builder().name(signup.getName()).email(signup.getEmail()).password(encodedPw).phoneNumber(signup.getPhoneNumber()).build();

        userRepository.save(signupUser);
    }

    public void fixUserInfo(UserFixDTO userFixDTO, Long userID) {
        Users findUser = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));

        findUser.changeUserInfo(userFixDTO.getName(),userFixDTO.getEmail(),userFixDTO.getPassword(), userFixDTO.getPhoneNumber());
    }

    public UserInfoDTO getUserInfo(String useremail) {
        Users findUser = userRepository.findByEmail(useremail).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));

        UserInfoDTO userInfo = UserInfoDTO.builder().name(findUser.getName()).email(findUser.getEmail()).phoneNumber(findUser.getPhoneNumber()).build();

        return userInfo;
    }

    public void userquit(Long userID) {
        Users findUser = userRepository.findById(userID).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));

        findUser.setStatus("회원 탈퇴");
    }

    public boolean validateDuplicate(String email) {
        Optional<Users> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }
}
