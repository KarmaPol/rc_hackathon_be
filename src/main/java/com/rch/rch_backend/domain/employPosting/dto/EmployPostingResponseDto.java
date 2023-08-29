package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import lombok.Builder;

import java.time.LocalDateTime;

public class EmployPostingResponseDto {
    private String postingName;
    private String companyUsername;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;

    @Builder
    public EmployPostingResponseDto(EmployPosting employPosting){
        this.postingName = employPosting.getPostingName();
        this.companyUsername = employPosting.getCompanyUser().getName();
        this.region = employPosting.getRegion();
        this.jobGroup = employPosting.getJobGroup();
        this.content = employPosting.getContent();
        this.techStack = employPosting.getTechStack();
        this.wage = employPosting.getWage();
        this.deadLine = employPosting.getDeadLine();
    }


}