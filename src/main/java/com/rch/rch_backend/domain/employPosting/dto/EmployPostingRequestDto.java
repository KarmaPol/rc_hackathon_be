package com.rch.rch_backend.domain.employPosting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployPostingRequestDto {
    private String postingName;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;

}