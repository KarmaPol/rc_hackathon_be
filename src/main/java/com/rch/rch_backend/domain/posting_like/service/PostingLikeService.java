package com.rch.rch_backend.domain.posting_like.service;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.posting_like.repository.PostingLikeRepository;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostingLikeService {

    private final PostingLikeRepository postingLikeRepository;
    private final EmployPostingRepository employPostingRepository;

    public void like(Long postId) {
        EmployPosting posting = validatePosting(postId);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostingLike like = PostingLike.builder()
                .employPosting(posting)
                .user(user).build();

        postingLikeRepository.save(like);
    }

    public void unlike(Long postId) {
        EmployPosting posting = validatePosting(postId);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostingLike like = postingLikeRepository.findByEmployPostingAndUser(posting, user)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좋아요입니다."));

        postingLikeRepository.delete(like);
    }

    public int countLike(Long postId) {
        EmployPosting posting = validatePosting(postId);
        return postingLikeRepository.countByEmployPosting(posting);
    }

    private EmployPosting validatePosting(Long postId) {
        return employPostingRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
