package com.rch.rch_backend.domain.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserFixDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
