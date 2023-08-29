package com.rch.rch_backend.domain.user;

import com.rch.rch_backend.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
