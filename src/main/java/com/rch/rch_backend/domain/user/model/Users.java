
package com.rch.rch_backend.domain.user.model;

import com.rch.rch_backend.domain.apply.Apply;
import com.rch.rch_backend.domain.common.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull @Column
    private String name;
    @NotNull @Column
    private String email;
    @NotNull
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @OneToMany(mappedBy = "users")
    private List<Apply> applies = new ArrayList<>();

    public Users(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Users() {

    }

    public void changeUserInfo(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void addApply(Apply apply) {
        this.applies.add(apply);
    }
}
