package com.rch.rch_backend.domain.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    @Builder
    public UserInfoDTO(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
