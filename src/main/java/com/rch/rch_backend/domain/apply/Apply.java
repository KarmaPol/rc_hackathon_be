package com.rch.rch_backend.domain.apply;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyId")
    private Long applyId;
    private String phoneNumber;
    private String resumeFile;
    private String recommender;

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;
}
