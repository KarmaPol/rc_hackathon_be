package com.rch.rch_backend.domain.apply.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rch.rch_backend.domain.apply.dto.ApplySaveRequestDto;
import com.rch.rch_backend.domain.apply.model.Apply;
import com.rch.rch_backend.domain.apply.model.ApplyStatus;
import com.rch.rch_backend.domain.apply.repository.ApplyRepository;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplyApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployPostingRepository employPostingRepository;

    EmployPosting defaultPosting;

    long postingId = 1L;
    long applyId = 1L;

    @BeforeEach
    void setMockMvc() {
        applyRepository.deleteAll();
        userRepository.deleteAll();
        employPostingRepository.deleteAll();

        defaultPosting = employPostingRepository.save(
                EmployPosting.builder()
                        .build());
    }

    Apply createDefaultApply() {
        return applyRepository.save(Apply.builder()
                .applyStatus(ApplyStatus.COMP)
                .phoneNumber("phoneNumber")
                .build());
    }
    @Test
    void register() throws Exception {
        // Given
        String url = "/api/v1/posts/{posting-id}/applies";
        ApplySaveRequestDto requestDto = ApplySaveRequestDto.builder()
                .phoneNumber("phoneNumber").build();

        // When
        ResultActions resultActions = mockMvc.perform(post(url, postingId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestDto)))
                        .andDo(print());


        //Then
        resultActions.andExpect(status().isCreated()).andDo(print());

        List<Apply> all = applyRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getApplyId()).isEqualTo(1);
    }

    @Test
    void deregister() throws Exception {
        // Given
        String url = "/api/v1/posts/1/applies/1";
        createDefaultApply();

        // When
        ResultActions resultActions = mockMvc.perform(put(url, postingId, applyId)).andDo(print());

        //Then
        resultActions.andExpect(status().isOk()).andDo(print());

        List<Apply> all = applyRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getApplyStatus()).isEqualTo(ApplyStatus.CANCEL);
    }

    @Test
    void applyList() throws Exception {
        // Given
        String url = "/api/v1/applyStatus";



        // When


        //Then

    }
}