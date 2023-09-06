package com.rch.rch_backend.configuration;

import com.rch.rch_backend.domain.user.model.UserRoles;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users findUser = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("이메일 혹은 비밀번호가 올바르지 않습니다."));

        if(findUser.getStatus() != null)
            throw new UsernameNotFoundException("이미 탈퇴한 회원입니다.");

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(findUser.getUserRoles())));

        return User.builder().username(findUser.getEmail()).password(findUser.getPassword())
                .authorities(authorities).build();
    }
}