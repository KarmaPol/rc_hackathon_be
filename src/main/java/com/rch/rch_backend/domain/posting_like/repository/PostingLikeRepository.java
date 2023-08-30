package com.rch.rch_backend.domain.posting_like.repository;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostingLikeRepository extends JpaRepository<PostingLike, Long> {
    Optional<PostingLike> findByEmployPostingAndUser(EmployPosting employPosting, Users users);

    int countByEmployPosting(EmployPosting employPosting);
}
