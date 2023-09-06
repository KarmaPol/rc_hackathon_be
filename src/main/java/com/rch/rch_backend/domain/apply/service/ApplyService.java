package com.rch.rch_backend.domain.apply.service;

import com.rch.rch_backend.domain.apply.dto.ApplyListResponseDto;
import com.rch.rch_backend.domain.apply.dto.ApplySaveRequestDto;
import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import com.rch.rch_backend.domain.apply.repository.ApplyRepository;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.user.model.NormalUser;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final EmployPostingRepository employPostingRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(Long employPostingId, ApplySaveRequestDto requestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));

        EmployPosting posting = employPostingRepository.findById(employPostingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공고입니다."));

        applyRepository.findByUser(user)
                .ifPresent(a -> new IllegalArgumentException("이미 지원한 공고입니다."));

        Apply apply = requestDto.toEntity(user, posting);
        applyRepository.save(apply);
        user.addApply(apply);

        return apply.getApplyId();
    }

    @Transactional
    public void cancel(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지원입니다."));

        apply.updateApplyStatus(ApplyStatus.CANCEL);
    }

    public List<ApplyListResponseDto> findApplies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));

        return applyRepository.findAllByUser(user)
                .stream().map(ApplyListResponseDto::new)
                .collect(Collectors.toList());
    }
}
