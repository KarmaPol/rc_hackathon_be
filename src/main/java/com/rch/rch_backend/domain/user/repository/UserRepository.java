package com.rch.rch_backend.domain.user.repository;

import com.rch.rch_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
