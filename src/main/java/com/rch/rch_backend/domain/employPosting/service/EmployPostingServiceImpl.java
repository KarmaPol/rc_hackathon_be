package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.user.response.UserInfoDTO;
import com.rch.rch_backend.domain.user.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
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
        UserInfoDTO currentUserInfo = userService.getUserAuthorities();

        if (currentUserInfo == null) {
            throw new RuntimeException("현재 사용자 정보를 가져올 수 없습니다.");
        }

        EmployPosting newPosting = new EmployPosting();
        newPosting.setPostingName(createdDto.getPostingName());
        newPosting.setRegion(createdDto.getRegion());
        newPosting.setJobGroup(createdDto.getJobGroup());
        newPosting.setContent(createdDto.getContent());
        newPosting.setTechStack(createdDto.getTechStack());
        newPosting.setWage(createdDto.getWage());
        newPosting.setDeadLine(createdDto.getDeadLine());

        User currentUserAccount = currentUserInfo.getUser();
        newPosting.setCompanyUser(currentUserAccount);

        EmployPosting savedPosting = employPostingRepository.save(newPosting);

        return new EmployPostingResponseDto(savedPosting);

        /*EmployPosting savedPosting = createdDto.toEntity(currentUserInfo.getUser().getName());
        savedPosting = employPostingRepository.save(savedPosting);

        EmployPosting employPosting = EmployPosting.builder()
                .postingName(createdDto.getPostingName())
                .region(createdDto.getRegion())
                .jobGroup(createdDto.getJobGroup())
                .content(createdDto.getContent())
                .techStack(createdDto.getTechStack())
                .wage(createdDto.getWage())
                .deadLine(createdDto.getDeadLine())
                .build();

        EmployPosting savedPosting = employPostingRepository.save(employPosting);

        return new EmployPostingResponseDto(savedPosting);*/
    }

    @Override
    public EmployPostingResponseDto updatePosting(Long postingId, EmployPostingRequestDto updatedDto) {
        UserInfoDTO currentUserInfo = userService.getUserAuthorities();
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

        return new EmployPostingResponseDto(updatedPosting);

        /*existingPosting.setPostingName(updatedDto.getPostingName());
        existingPosting.setRegion(updatedDto.getRegion());
        existingPosting.setJobGroup(updatedDto.getJobGroup());
        existingPosting.setContent(updatedDto.getContent());
        existingPosting.setTechStack(updatedDto.getTechStack());
        existingPosting.setWage(updatedDto.getWage());
        existingPosting.setDeadLine(updatedDto.getDeadLine());
        // 체인 연결이 안됨.

        EmployPosting updatedPosting = employPostingRepository.save(existingPosting);

        return new EmployPostingResponseDto(updatedPosting);*/
    }

    @Override
    public void deletePosting(Long postingId) {
        UserInfoDTO currentUserInfo = userService.getUserAuthorities();

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
}
