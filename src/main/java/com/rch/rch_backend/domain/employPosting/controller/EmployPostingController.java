package com.rch.rch_backend.domain.employPosting.controller;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.service.EmployPostingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "채용포스팅 컨트롤러")
@RestController
@RequestMapping("/posts")
public class EmployPostingController {

    private final EmployPostingServiceImpl employPostingService;

    public EmployPostingController(EmployPostingServiceImpl employPostingService) {
        this.employPostingService = employPostingService;
    }

    @PostMapping("")
    @ApiOperation(value = "기업유저 채용포스팅 생성", notes = "기업 유저용 채용포스팅을 생성한다.")
    public ResponseEntity<EmployPostingResponseDto> createPosting(@RequestBody EmployPostingRequestDto requestDto){
        EmployPostingResponseDto responseDto = employPostingService.createPosting(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{postingId}")
    @ApiOperation(value = "기업유저 채용포스팅 수정", notes = "기업 유저용 채용포스팅을 수정한다.")
    public ResponseEntity<EmployPostingResponseDto> updatePosting(@PathVariable Long postingId, @RequestBody EmployPostingRequestDto updatedDto){
        EmployPostingResponseDto responseDto = employPostingService.updatePosting(postingId, updatedDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postingId}")
    @ApiOperation(value = "기업유저 채용포스팅 삭제", notes = "기업 유저용 채용포스팅을 삭제한다.")
    public ResponseEntity<Void> deletePosting(@PathVariable Long postingId){
        employPostingService.deletePosting(postingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    @ApiOperation(value = "채용포스팅 전체 조회", notes = "전체 채용포스팅을 조회한다.")
    public ResponseEntity<List<EmployPostingResponseDto>> getAllPostings(){
        List<EmployPostingResponseDto> responseDtoList = employPostingService.getAllPostings();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{postingId}")
    @ApiOperation(value = "특정 채용포스팅 조회", notes = "특정 채용포스팅을 조회한다.")
    public ResponseEntity<EmployPostingResponseDto> getPostingById(@PathVariable Long postingId){
        EmployPostingResponseDto responseDto = employPostingService.getEmployPostingById(postingId);
        return ResponseEntity.ok(responseDto);
    }
}