package com.rch.rch_backend.domain.employPosting.controller;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.service.EmployPostingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployPostingController {

    private final EmployPostingServiceImpl employPostingService;

    public EmployPostingController(EmployPostingServiceImpl employPostingService) {
        this.employPostingService = employPostingService;
    }


    @PostMapping("")
    public ResponseEntity<EmployPostingResponseDto> createPosting(@RequestBody EmployPostingRequestDto requestDto){
        EmployPostingResponseDto responseDto = employPostingService.createPosting(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{postingId}")
    public ResponseEntity<EmployPostingResponseDto> updatePosting(@PathVariable Long postingId, @RequestBody EmployPostingRequestDto updatedDto){
        EmployPostingResponseDto responseDto = employPostingService.updatePosting(postingId, updatedDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<Void> deletePosting(@PathVariable Long postingId){
        employPostingService.deletePosting(postingId);
        return ResponseEntity.noContent().build();
    }

}
