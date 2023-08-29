package com.rch.rch_backend.domain.employPosting.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employ_posting")
@Builder
public class EmployPosting {

    @Id
    @Column(name = "posting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "posting_name")
    private String postingName;

    @Column(name = "region")
    private String region;

    @Column(name = "jobgroup")
    private String jobGroup;

    @Column(name = "employ_statement")
    private String employStatement;

    @Column(name = "content")
    private String content;

    @Column(name = "techstack")
    private String techStack;

    @Column(name = "wage")
    private Long wage;

    @Column(name = "deadline")
    private LocalDateTime deadLine;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


}
