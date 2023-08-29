package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;

public interface EmployPositngService {

    EmployPostingResponseDto createPosting(EmployPostingRequestDto createDto);
    EmployPostingResponseDto updatePosting(Long postingId, EmployPostingRequestDto updatedDto);
    void deletePosting(Long postingId);
}
