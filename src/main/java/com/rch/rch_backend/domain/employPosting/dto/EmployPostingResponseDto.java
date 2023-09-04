package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployPostingResponseDto {
    private String postingName;
    private String Username;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;


    public EmployPostingResponseDto(EmployPosting employPosting) {
        this.postingName = employPosting.getPostingName();
        // this.Username = employPosting.getUser().getUsername();
        this.region = employPosting.getRegion();
        this.jobGroup = employPosting.getJobGroup();
        this.content = employPosting.getContent();
        this.techStack = employPosting.getTechStack();
        this.wage = employPosting.getWage();
        this.deadLine = employPosting.getDeadLine();
    }
}