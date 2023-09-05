package com.rch.rch_backend.domain.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SigninDTO {
    private String username;
    private String password;
}
