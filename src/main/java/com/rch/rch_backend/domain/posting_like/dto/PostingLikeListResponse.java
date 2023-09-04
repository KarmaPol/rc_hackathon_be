package com.rch.rch_backend.domain.posting_like.dto;

import com.rch.rch_backend.domain.user.model.Users;
import lombok.Getter;

@Getter
public class PostingLikeListResponse {
    private String name;

    public PostingLikeListResponse(Users user) {
        this.name = user.getName();
    }
}
