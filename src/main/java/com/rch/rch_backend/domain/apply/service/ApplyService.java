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

    // TODO: 테스트용. 스프링 시큐리티 부분이 완성되면 모킹 객체를 없애야
    private final UserRepository userRepository;
    NormalUser user;
    public NormalUser forTest() {
        return userRepository.save(NormalUser.builder()
                .email("email@email.com")
                .name("name")
                .password("password")
                .phoneNumber("phoneNumber")
                .build());
    }

    @Transactional
    public Long save(Long employPostingId, ApplySaveRequestDto requestDto) {
        //Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // TODO: 테스트용. 스프링 시큐리티 부분이 완성되면 모킹 객체를 없애야
        user = forTest();

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
        // Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // TODO: 테스트용. 스프링 시큐리티 부분이 완성되면 모킹 객체를 없애야
        user = forTest();

        return applyRepository.findAllByUser(user)
                .stream().map(ApplyListResponseDto::new)
                .collect(Collectors.toList());
    }
}
