package com.rch.rch_backend.domain.employPosting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingResponseDto;
import com.rch.rch_backend.domain.employPosting.service.EmployPostingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployPostingServiceImpl employPostingService;

    private EmployPostingRequestDto requestDto;
    private EmployPostingResponseDto responseDto;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 생성
        requestDto = EmployPostingRequestDto.builder()
                .postingName("Test Posting")
                .region("Test Region")
                .jobGroup("Test Job Group")
                .content("Test Content")
                .techStack("Test Tech Stack")
                .wage(1000L)
                .deadLine(LocalDateTime.now().plusDays(30))
                .build();

        responseDto = EmployPostingResponseDto.builder()
                .postingName("Test Posting")
                .region("Test Region")
                .jobGroup("Test Job Group")
                .content("Test Content")
                .techStack("Test Tech Stack")
                .wage(1000L)
                .deadLine(LocalDateTime.now().plusDays(30))
                .build();
    }

    @Test
    @ResponseStatus(HttpStatus.CREATED)
    @WithMockUser(roles = "COMPANY_USER")
    void createPosting() throws Exception {
        // Mock Service의 반환 값을 설정
        Mockito.when(employPostingService.createPosting(requestDto)).thenReturn(responseDto);

        // POST 요청을 보내고 응답 확인
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postingName").value("Test Posting"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region").value("Test Region"));
    }

    @Test
    @WithMockUser(roles = "COMPANY_USER")
    void updatePosting() throws Exception {
        // Mock Service의 반환 값을 설정
        Mockito.when(employPostingService.updatePosting(Mockito.anyLong(), Mockito.eq(requestDto)))
                .thenReturn(responseDto);

        // PATCH 요청을 보내고 응답 확인
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/posts/1") // 여기서 1은 업데이트할 포스팅의 ID입니다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postingName").value("Test Posting"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region").value("Test Region"));
    }

    @Test
    @WithMockUser(roles = "COMPANY_USER")
    void deletePosting() throws Exception {
        // DELETE 요청을 보내고 응답 확인
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/posts/1")) // 여기서 1은 삭제할 포스팅의 ID입니다.
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "COMPANY_USER")
    void getAllPostings() throws Exception {
        // Mock Service의 반환 값을 설정
        List<EmployPostingResponseDto> responseDtoList = new ArrayList<>();
        responseDtoList.add(responseDto);
        Mockito.when(employPostingService.getAllPostings()).thenReturn(responseDtoList);

        // GET 요청을 보내고 응답 확인
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].postingName").value("Test Posting"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].region").value("Test Region"));
    }

    @Test
    @WithMockUser(roles = "COMPANY_USER")
    void getPostingById() throws Exception {
        // Mock Service의 반환 값을 설정
        Mockito.when(employPostingService.getEmployPostingById(Mockito.anyLong()))
                .thenReturn(responseDto);

        // GET 요청을 보내고 응답 확인
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts/1")) // 여기서 1은 조회할 포스팅의 ID입니다.
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postingName").value("Test Posting"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region").value("Test Region"));
    }
}
