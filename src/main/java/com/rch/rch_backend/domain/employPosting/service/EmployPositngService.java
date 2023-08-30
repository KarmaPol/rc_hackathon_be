package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;

import java.util.List;

public interface EmployPositngService {

    List<EmployPostingResponseDto> getAllPostings();
    EmployPostingResponseDto getEmployPostingById(Long postingId);
    EmployPostingResponseDto createPosting(EmployPostingRequestDto createDto);
    EmployPostingResponseDto updatePosting(Long postingId, EmployPostingRequestDto updatedDto);
    void deletePosting(Long postingId);
}
