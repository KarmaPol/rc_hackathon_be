package com.rch.rch_backend.domain.user.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class SignupDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    @Builder
    public SignupDTO(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
