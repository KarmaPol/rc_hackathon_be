package com.rch.rch_backend.domain.posting_like.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rch.rch_backend.domain.apply.repository.ApplyRepository;
import com.rch.rch_backend.domain.employPosting.model.EmployPosting;
import com.rch.rch_backend.domain.employPosting.repository.EmployPostingRepository;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.posting_like.repository.PostingLikeRepository;
import com.rch.rch_backend.domain.user.model.NormalUser;
import com.rch.rch_backend.domain.user.model.Users;
import com.rch.rch_backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PostingLikeApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostingLikeRepository postingLikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployPostingRepository employPostingRepository;

    EmployPosting defaultPosting;

    long postingId = 1L;

    @BeforeEach
    void setMockMvc() {
        postingLikeRepository.deleteAll();
        employPostingRepository.deleteAll();
        userRepository.deleteAll();

        defaultPosting = employPostingRepository.save(
                EmployPosting.builder()
                        .build());
    }

    PostingLike createDefaultPostingLike() {
        return postingLikeRepository.save(PostingLike.builder()
                .employPosting(defaultPosting)
                .build());
    }

    @WithMockUser(username = "name")
    @Test
    void like() throws Exception {
        // Given
        String url = "/api/v1/posts/1/likes";

        // Principal principal = Mockito.mock(Principal.class);
        // Mockito.when(principal.getName()).thenReturn("name");

        // When
        ResultActions resultActions = mockMvc.perform(post(url, postingId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        // Then
        resultActions.andExpect(status().isCreated()).andDo(print());

        List<PostingLike> all = postingLikeRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void unlike() throws Exception {
        // Given
        String url = "/api/v1/posts/1/likes";
        createDefaultPostingLike();

        // When
        ResultActions resultActions = mockMvc.perform(delete(url, postingId)).andDo(print());

        // Then
        resultActions.andExpect(status().isOk()).andDo(print());

        List<PostingLike> all = postingLikeRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void viewLikes() throws Exception {
        // Given
        String url = "/api/v1/posts/1/likes";
        createDefaultPostingLike();

        // When
        ResultActions resultActions = mockMvc.perform(get(url, postingId)).andDo(print());

        // Then
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void viewLikeUsers() throws Exception {
        // Given
        String url = "/api/v1/posts/1/likes/users";
        Users user = userRepository.save(NormalUser.builder()
                .email("email@email.com")
                .name("name")
                .password("password")
                .phoneNumber("phoneNumber")
                .build());

        postingLikeRepository.save(PostingLike.builder()
                .employPosting(defaultPosting)
                .user(user)
                .build());

        // When
        ResultActions resultActions = mockMvc.perform(get(url, postingId)).andDo(print());

        // Then
        resultActions.andExpect(status().isOk()).andDo(print());
    }

}