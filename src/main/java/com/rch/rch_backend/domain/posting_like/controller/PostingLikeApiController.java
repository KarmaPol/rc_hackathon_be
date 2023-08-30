package com.rch.rch_backend.domain.posting_like.controller;

import com.rch.rch_backend.domain.posting_like.service.PostingLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "채용 정보 좋아요 API 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api/v1/postings/{posting-id}/likes")
@RestController
public class PostingLikeApiController {

    private final PostingLikeService postingLikeService;

    @PostMapping
    @ApiOperation(value = "일반 유저 좋아요 등록", notes = "일반 유저가 채용 정보에 대한 좋아요를 남긴다.")
    public void like(@PathVariable(name = "posting-id") Long postingId) {
        postingLikeService.like(postingId);
    }

    @DeleteMapping
    @ApiOperation(value = "일반 유저 좋아요 취소", notes = "일반 유저가 채용 정보에 남긴 좋아요를 취소한다.")
    public void unlike(@PathVariable(name = "posting-id") Long postingId) {
        postingLikeService.unlike(postingId);
    }

    @GetMapping
    @ApiOperation(value = "채용 정보 좋아요 수 집계", notes = "특정 채용 정보가 받은 좋아요 수를 집계한다.")
    public ResponseEntity<Integer> viewLikes(@PathVariable(name = "posting-id") Long postingId) {
        Integer count = postingLikeService.countLike(postingId);
        return ResponseEntity.ok().body(count);
    }
}
