package com.rch.rch_backend.domain.employPosting.repository;

import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployPostingRepository extends JpaRepository<EmployPosting, Long> {
}
