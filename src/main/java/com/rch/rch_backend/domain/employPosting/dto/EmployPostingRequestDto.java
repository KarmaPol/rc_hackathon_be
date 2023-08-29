package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.user.model.CompanyUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EmployPostingRequestDto {
    private String postingName;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;

    public EmployPostingRequestDto(String postingName, String region, String jobGroup, String content,
                       String techStack, Long wage, LocalDateTime deadLine) {
        this.postingName = postingName;
        this.region = region;
        this.jobGroup = jobGroup;
        this.content = content;
        this.techStack = techStack;
        this.wage = wage;
        this.deadLine = deadLine;
    }

    /*public EmployPosting toEntity(CompanyUser companyUser){
        return EmployPosting.builder()
                .postingName(this.postingName)
                .region(this.region)
                .jobGroup(this.jobGroup)
                .content(this.content)
                .techStack(this.techStack)
                .wage(this.wage)
                .deadLine(this.deadLine)
                .build();
    }*/
}