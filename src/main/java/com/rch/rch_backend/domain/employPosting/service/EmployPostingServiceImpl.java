package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;

    public EmployPostingServiceImpl(EmployPostingRepository employPostingRepository, UserService userService) {
        this.employPostingRepository = employPostingRepository;
        this.userService = userService;
    }


    @Override
    public EmployPostingResponseDto createPosting(EmployPostingRequestDto createdDto) {
        // 인증된 사용자 정보 가져오기
        Authentication currentUserInfo = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserInfo == null) {
            throw new RuntimeException("현재 사용자 정보를 가져올 수 없습니다.");
        }

        /*String currentUsername = currentUserInfo.getName();
        UserDetails userDetails = userService.loadUserByUsername(currentUsername);

        if (userDetails == null) {
            throw new RuntimeException("현재 사용자 정보를 가져올 수 없습니다.");
        }

        User currentUser = new User(userDetails);*/


        EmployPosting newPosting = EmployPosting.builder()
                .postingName(createdDto.getPostingName())
                .region(createdDto.getRegion())
                .jobGroup(createdDto.getJobGroup())
                .content(createdDto.getContent())
                .techStack(createdDto.getTechStack())
                .wage(createdDto.getWage())
                .deadLine(createdDto.getDeadLine())
                //.user(currentUser)
                .build();

        EmployPosting savedPosting = employPostingRepository.save(newPosting);

        return EmployPostingResponseDto.builder()
                .postingName(savedPosting.getPostingName())
                .region(savedPosting.getRegion())
                .jobGroup(savedPosting.getJobGroup())
                .content(savedPosting.getContent())
                .techStack(savedPosting.getTechStack())
                .wage(savedPosting.getWage())
                .deadLine(savedPosting.getDeadLine())
                //.Username(savedPosting.getUser().getUsername())
                .build();
    }

    @Override
    public EmployPostingResponseDto updatePosting(Long postingId, EmployPostingRequestDto updatedDto) {
        Authentication currentUserInfo = SecurityContextHolder.getContext().getAuthentication();
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(currentUserInfo == null){
            throw new RuntimeException("현재 사용자 정보를 가져올 수 없습니다.");
        }

        EmployPosting existingPosting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));

        try{
            if(!existingPosting.getCompanyUser().getName().equals(currentUserInfo.getName())){
                throw new AccessDeniedException("해당 게시물을 수정할 권한이 없습니다.");
            }
        } catch(AccessDeniedException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.", e);
        }

        existingPosting.updateFromDto(updatedDto); // 업데이트 메서드 활용

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
    public void deletePosting(Long postingId) {
        Users currentUserInfo = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUserInfo == null) {
            throw new RuntimeException("현재 사용자 정보를 가져올 수 없습니다.");
        }

        EmployPosting existingPosting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));

        try {
            if (!existingPosting.getCompanyUser().getName().equals(currentUserInfo.getName())) {
                throw new AccessDeniedException("해당 게시물을 삭제할 권한이 없습니다.");
            }
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.", e);
        }

        employPostingRepository.delete(existingPosting);
    }

    @Override
    public List<EmployPostingResponseDto> getAllPostings() {
        List<EmployPosting> employPostings = employPostingRepository.findAll();

        return employPostings.stream()
                .map(EmployPostingResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public EmployPostingResponseDto getEmployPostingById(Long postingId) {
        EmployPosting employPosting = employPostingRepository.findById(postingId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시물을 찾을 수 없습니다."));

        return new EmployPostingResponseDto(employPosting);
    }
}
