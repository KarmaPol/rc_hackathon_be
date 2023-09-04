package com.rch.rch_backend.configuration;

import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users findUser = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("잘못된 ID/PW 입니다."));

        return User.builder().username(findUser.getName()).password(findUser.getPassword())
                .roles(String.valueOf(findUser.getUserRoles())).build();
    }
}
