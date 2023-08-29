package com.rch.rch_backend.domain.employPosting.dto;

import com.sun.istack.NotNull;

import java.time.LocalDateTime;


public class ReqEmployPosting {
    public static class Create{
        @NotNull
        private String postingName;
        @NotNull
        private String region;
        @NotNull
        private String jobGroup;
        @NotNull
        private String content;
        @NotNull
        private String techStack;
        @NotNull
        private Long wage;
        @NotNull
        private LocalDateTime deadLine;
    }

    public static class Update{
        @NotNull
        private String postingName;
        @NotNull
        private String region;
        @NotNull
        private String jobGroup;
        @NotNull
        private String content;
        @NotNull
        private String techStack;
        @NotNull
        private Long wage;
        @NotNull
        private LocalDateTime deadLine;
    }

}
