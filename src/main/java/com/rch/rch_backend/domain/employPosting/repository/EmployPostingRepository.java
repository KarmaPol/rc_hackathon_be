package com.rch.rch_backend.domain.employPosting.repository;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployPostingRepository extends JpaRepository<EmployPosting, Long> {
    @EntityGraph(attributePaths = "user")
    List<Apply> findAllByUser(Users users);
}
