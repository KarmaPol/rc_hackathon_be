package com.rch.rch_backend.domain.posting_like.dto;

import lombok.Getter;

@Getter
public class PostingLikeCountResponse {
    private int count;

    public PostingLikeCountResponse(int count) {
        this.count = count;
    }
}
