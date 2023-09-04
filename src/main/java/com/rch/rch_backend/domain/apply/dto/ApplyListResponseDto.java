package com.rch.rch_backend.domain.apply.dto;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplyListResponseDto {
    private String postingName;
    private LocalDateTime cratedDate;
    private ApplyStatus applyStatus;
    private String recommender;


    public ApplyListResponseDto(Apply apply) {
        this.postingName = apply.getEmployPosting().getPostingName();
        this.cratedDate = apply.getCreatedDate();
        this.applyStatus = apply.getApplyStatus();
        this.recommender = apply.getRecommender();
    }
}
