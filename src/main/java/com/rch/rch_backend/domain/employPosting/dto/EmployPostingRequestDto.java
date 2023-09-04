package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.user.model.CompanyUser;
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