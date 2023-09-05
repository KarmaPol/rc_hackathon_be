package com.rch.rch_backend.domain.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class SigninDTO {
    private String username;
    private String password;
}
