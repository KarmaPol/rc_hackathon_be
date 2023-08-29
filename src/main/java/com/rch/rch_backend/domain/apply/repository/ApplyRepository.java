package com.rch.rch_backend.domain.apply.repository;

import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findByUsers(Users users);
    List<Apply> findAllByUsers(Users users);
}
