package com.rch.rch_backend.domain.apply.controller;

import com.rch.rch_backend.domain.apply.dto.ApplyListResponseDto;
import com.rch.rch_backend.domain.apply.dto.ApplySaveRequestDto;
import com.rch.rch_backend.domain.apply.service.ApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "지원 API 컨트롤러")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ApplyApiController {

    private final ApplyService applyService;

    @PostMapping("/postings/{posting-id}/applies")
    @ApiOperation(value = "일반 유저 채용 지원", notes = "일반 유저가 채용 정보에 대한 지원을 수행한다.")
    public ResponseEntity<Void> register(@RequestBody ApplySaveRequestDto requestDto,
                                   @PathVariable(name = "posting-id") Long postingId) {

        applyService.save(postingId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/postings/{posting-id}/applies/{apply-id}")
    @ApiOperation(value = "일반 유저 채용 지원 취소", notes = "일반 유저가 채용 정보에 대한 지원을 취소한다.")
    public ResponseEntity<Void> deregister(@PathVariable(name = "apply-id") Long applyId) {

        applyService.cancel(applyId);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/applyStatus")
    @ApiOperation(value = "일반 유저 지원 현황 조회", notes = "일반 유저가 지원 현황을 조회한다.")
    public ResponseEntity<List<ApplyListResponseDto>> applyList() {

        List<ApplyListResponseDto> applies = applyService.findApplies();

        return ResponseEntity.ok()
                .body(applies);
    }
}
