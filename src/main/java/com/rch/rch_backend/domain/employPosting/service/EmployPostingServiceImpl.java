package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.apply.dto.ApplyListResponseDto;
import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.user.model.CompanyUser;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployPostingServiceImpl implements EmployPositngService {

    private final EmployPostingRepository employPostingRepository;
    private final UserRepository userRepository;

    public EmployPostingServiceImpl(EmployPostingRepository employPostingRepository, UserRepository userRepository) {
        this.employPostingRepository = employPostingRepository;
        this.userRepository = userRepository;
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANYUSER')")
    public EmployPostingResponseDto createPosting(EmployPostingRequestDto createdDto) {
        // 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));

        EmployPosting newPosting = EmployPosting.builder()
                .postingName(createdDto.getPostingName())
                .region(createdDto.getRegion())
                .jobGroup(createdDto.getJobGroup())
                .content(createdDto.getContent())
                .techStack(createdDto.getTechStack())
                .wage(createdDto.getWage())
                .deadLine(createdDto.getDeadLine())
                .user(user)
                .build();

        EmployPosting savedPosting = employPostingRepository.save(newPosting);

        return EmployPostingResponseDto.builder()
                .postingId(savedPosting.getId())
                .postingName(savedPosting.getPostingName())
                .region(savedPosting.getRegion())
                .jobGroup(savedPosting.getJobGroup())
                .content(savedPosting.getContent())
                .techStack(savedPosting.getTechStack())
                .wage(savedPosting.getWage())
                .deadLine(savedPosting.getDeadLine())
                .username(user.getName()) // 사용자 이름 설정
                .build();
    }



    @Override
    @PreAuthorize("hasRole('ROLE_COMPANYUSER')")
    public EmployPostingResponseDto updatePosting(Long postingId, EmployPostingRequestDto updatedDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));

        EmployPosting existingPosting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));


        existingPosting.updateFromDto(updatedDto);
        EmployPosting updatedPosting = employPostingRepository.save(existingPosting);

        return EmployPostingResponseDto.builder()
                .postingName(updatedPosting.getPostingName())
                .region(updatedPosting.getRegion())
                .jobGroup(updatedPosting.getJobGroup())
                .content(updatedPosting.getContent())
                .techStack(updatedPosting.getTechStack())
                .wage(updatedPosting.getWage())
                .deadLine(updatedPosting.getDeadLine())
                .build();
    }


    @Override
    @PreAuthorize("hasRole('ROLE_COMPANYUSER')")
    public void deletePosting(Long postingId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));

        EmployPosting existingPosting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));

        employPostingRepository.delete(existingPosting);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_COMPANYUSER')")
    public List<EmployPostingResponseDto> getAllPostings() {
        List<EmployPosting> postings = employPostingRepository.findAll();
        return postings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    @PreAuthorize("hasRole('ROLE_COMPANYUSER')")
    public EmployPostingResponseDto getEmployPostingById(Long postingId) {
        EmployPosting posting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));

        return convertToDto(posting);
    }


    private EmployPostingResponseDto convertToDto(EmployPosting posting) {
        EmployPostingResponseDto dto = new EmployPostingResponseDto();
        dto.setPostingId(posting.getId());
        dto.setPostingName(posting.getPostingName());
        dto.setRegion(posting.getRegion());
        dto.setJobGroup(posting.getJobGroup());
        dto.setContent(posting.getContent());
        dto.setTechStack(posting.getTechStack());
        dto.setWage(posting.getWage());
        dto.setDeadLine(posting.getDeadLine());

        return dto;
    }

}
