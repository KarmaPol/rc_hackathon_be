package com.rch.rch_backend.domain.apply.dto;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplySaveRequestDto {
    private String phoneNumber;
    private String recommender;
    private String resumeFile;

    public Apply toEntity(Users user, EmployPosting posting) {
        return Apply.builder()
                .phoneNumber(this.phoneNumber)
                .recommender(this.recommender)
                .resumeFile(this.resumeFile)
                .applyStatus(ApplyStatus.COMP)
                .user(user)
                .employPosting(posting)
                .build();
    }

    @Builder
    public ApplySaveRequestDto(String phoneNumber, String recommender, String resumeFile) {
        this.phoneNumber = phoneNumber;
        this.recommender = recommender;
        this.resumeFile = resumeFile;
    }

    public ApplySaveRequestDto(Apply apply) {
        this.phoneNumber = apply.getPhoneNumber();
        this.recommender = apply.getRecommender();
        this.resumeFile = apply.getResumeFile();
    }
}
