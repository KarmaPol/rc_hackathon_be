package com.rch.rch_backend.domain.posting_like.controller;

import com.rch.rch_backend.domain.posting_like.dto.PostingLikeListResponse;
import com.rch.rch_backend.domain.posting_like.service.PostingLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "채용 정보 좋아요 API 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/posts/{posting-id}/likes")
@RestController
public class PostingLikeApiController {

    private final PostingLikeService postingLikeService;

    @PostMapping
    @ApiOperation(value = "일반 유저 좋아요 등록", notes = "일반 유저가 채용 정보에 대한 좋아요를 남긴다.")
    public ResponseEntity<Void> like(@PathVariable(name = "posting-id") Long postingId) {

        postingLikeService.like(postingId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    @ApiOperation(value = "일반 유저 좋아요 취소", notes = "일반 유저가 채용 정보에 남긴 좋아요를 취소한다.")
    public ResponseEntity<Void> unlike(@PathVariable(name = "posting-id") Long postingId) {

        postingLikeService.unlike(postingId);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping
    @ApiOperation(value = "채용 정보 좋아요 수 집계", notes = "특정 채용 정보가 받은 좋아요 수를 집계한다.")
    public ResponseEntity<Integer> viewLikes(@PathVariable(name = "posting-id") Long postingId) {

        Integer count = postingLikeService.countLike(postingId);

        return ResponseEntity.ok()
                .body(count);
    }

    @GetMapping("/users")
    @ApiOperation(value = "채용 정보에 좋아요를 누른 일반 회원 집계", notes = "특정 채용 정보에 좋아요를 누른 회원 이름 목록을 반환한다.")
    public ResponseEntity<List<PostingLikeListResponse>> viewLikeUsers(@PathVariable(name = "posting-id") Long postingId) {

        List<PostingLikeListResponse> postingLikeUsers = postingLikeService.likeUsers(postingId);

        return ResponseEntity.ok()
                .body(postingLikeUsers);
    }
}
