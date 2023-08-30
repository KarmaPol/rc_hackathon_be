package com.rch.rch_backend.domain.apply.repository;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    @EntityGraph(attributePaths = "user")
    Optional<Apply> findByUser(Users users);
    @EntityGraph(attributePaths = "user")
    List<Apply> findAllByUser(Users users);
}
