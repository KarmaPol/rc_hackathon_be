package com.rch.rch_backend.domain.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Entity
@NoArgsConstructor
public class CompanyUser extends Users{
    @Builder
    public CompanyUser(String name, String email, String password, String phoneNumber) {
        super(name, email, password, phoneNumber);
        this.userRoles = UserRoles.ROLE_COMPANYUSER;
    }
}
