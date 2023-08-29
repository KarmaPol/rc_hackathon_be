package com.rch.rch_backend.domain.apply;

import com.rch.rch_backend.domain.common.BaseEntity;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Apply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyId")
    private Long applyId;
    private String phoneNumber;
    private String resumeFile;
    private String recommender;

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    /**
     * 채용 정보 매핑 필요
     */

}
