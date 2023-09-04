package com.rch.rch_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
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
                        .antMatchers("/users/sign-in", "/users/sign-up", "/users/company/sign-in").permitAll()
                        .antMatchers("/posts/{posting-id}/likes/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/posts").permitAll()
//                        .antMatchers("/v2/api-docs",
//                                "/swagger-resources",
//                                "/swagger-resources/**",
//                                "/configuration/ui",
//                                "/configuration/security",
//                                "/swagger-ui.html",
//                                "/webjars/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**").permitAll()
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/posts/{postingId}").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
//                        .loginPage("/login") // 프론트 url
                        .loginProcessingUrl("/users/sign-in") // 백 url
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/"))
                .logout(withDefaults())
        ;

        return http.build();
    }
}