package com.rch.rch_backend.domain.employPosting.dto;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;

import java.time.LocalDateTime;

public class RespEmployPosting {

    public static class Info {
        private String postingName;
        private String region;
        private String jobGroup;
        private String content;
        private String techStack;
        private Long wage;
        private LocalDateTime deadLine;
    }

    public static RespEmployPosting.Info of(EmployPosting employPosting) {
        return null;
    }
}