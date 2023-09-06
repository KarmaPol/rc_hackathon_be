package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployPostingResponseDto {
    private Long postingId;
    private String postingName;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;
    private String username;


    public EmployPostingResponseDto(EmployPosting employPosting, Users user) {
        this.postingId = employPosting.getId();
        this.postingName = employPosting.getPostingName();
        this.region = employPosting.getRegion();
        this.jobGroup = employPosting.getJobGroup();
        this.content = employPosting.getContent();
        this.techStack = employPosting.getTechStack();
        this.wage = employPosting.getWage();
        this.deadLine = employPosting.getDeadLine();
        this.username = user.getName();
    }
}