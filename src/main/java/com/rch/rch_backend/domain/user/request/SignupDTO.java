package com.rch.rch_backend.domain.user.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Getter
public class SignupDTO {
    @NotBlank(message = "이름에는 공백이 들어갈 수 없습니다.")
    private String name;
    @Email(message = "이메일 형식이 아닙니다.(abc@abc.com)")
    private String email;
    @NotBlank(message = "비밀번호에는 공백이 들어갈 수 없습니다.")
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
