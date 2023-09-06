package com.rch.rch_backend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rch.rch_backend.configuration.handler.Http401Handler;
import com.rch.rch_backend.configuration.handler.Http403Handler;
import com.rch.rch_backend.domain.exception.response.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final ObjectMapper objectMapper;

    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .antMatchers("/","/users/sign-in", "/users/sign-up","/users/sign-out", "/users/company/sign-in").permitAll()
                        .antMatchers("/posts/{posting-id}/likes/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/posts").hasRole("COMPANYUSER")
                        .antMatchers(HttpMethod.DELETE, "/posts").hasRole("COMPANYUSER")
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginProcessingUrl("/users/sign-in") // 백 url
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(((request, response, authentication) -> {
                            UserDetails user = (UserDetails) authentication.getPrincipal();
                            String username = user.getUsername();
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write(username + " login success");
                        }))
                        .failureHandler(((request, response, exception) -> {
                            ErrorResponse errorResponse = ErrorResponse.builder().code("400").message("이메일 혹은 비밀번호가 올바르지 않습니다.").build();

                            String json = objectMapper.writeValueAsString(errorResponse);

                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.getWriter().write(json);
                        })))
                .logout(logout -> logout
                        .logoutUrl("/users/sign-out")
                        .logoutSuccessHandler(((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("logout success");
                        })))
                .exceptionHandling(e -> {
                        e.accessDeniedHandler(new Http403Handler(new ObjectMapper()));
                        e.authenticationEntryPoint(new Http401Handler(new ObjectMapper()));
                    }
                )
        ;

        return http.build();
    }
}