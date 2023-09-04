package com.rch.rch_backend.domain.posting_like.service;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.posting_like.dto.PostingLikeListResponse;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.posting_like.repository.PostingLikeRepository;
import com.rch.rch_backend.domain.user.model.NormalUser;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostingLikeService {

    private final PostingLikeRepository postingLikeRepository;
    private final EmployPostingRepository employPostingRepository;

    // TODO: 테스트용. 스프링 시큐리티 부분이 완성되면 모킹 객체를 없애야
    private final UserRepository userRepository;
    NormalUser user;
    public Users forTest() {
        return userRepository.save(NormalUser.builder()
                .email("email@email.com")
                .name("name")
                .password("password")
                .phoneNumber("phoneNumber")
                .build());
    }


    @Transactional
    public void like(Long postId) {
        EmployPosting posting = validatePosting(postId);
        // Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostingLike like = PostingLike.builder()
                .employPosting(posting)
                .user(user).build();

        postingLikeRepository.save(like);
    }

    @Transactional
    public void unlike(Long postId) {
        EmployPosting posting = validatePosting(postId);
        // Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostingLike like = postingLikeRepository.findByEmployPostingAndUser(posting, user)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좋아요입니다."));

        postingLikeRepository.delete(like);
    }

    public int countLike(Long postId) {
        EmployPosting posting = validatePosting(postId);
        return postingLikeRepository.countByEmployPosting(posting);
    }

    public List<PostingLikeListResponse> likeUsers(Long postId) {
        EmployPosting posting = validatePosting(postId);
        List<PostingLike> postingLikes = postingLikeRepository.findAllByEmployPosting(posting);

        return postingLikes.stream()
                .map(postingLike -> new PostingLikeListResponse(postingLike.getUser()))
                .collect(Collectors.toList());
    }

    private EmployPosting validatePosting(Long postId) {
        return employPostingRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 공고입니다."));
    }
}
