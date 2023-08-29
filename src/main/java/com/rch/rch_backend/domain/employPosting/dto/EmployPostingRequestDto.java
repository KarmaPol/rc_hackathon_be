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
    private String companyUsername;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;

    public EmployPosting toEntity(CompanyUser companyUser){
        return EmployPosting.builder()
                .postingName(this.postingName)
                .recommender(this.recommender)
                .resumeFile(this.resumeFile)
                .applyStatus(ApplyStatus.COMP)
                .user(user)
                .employPosting(posting)
                .build();
    }
}