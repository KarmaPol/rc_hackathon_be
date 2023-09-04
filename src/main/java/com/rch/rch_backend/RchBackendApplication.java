package com.rch.rch_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RchBackendApplication.class, args);
    }

}