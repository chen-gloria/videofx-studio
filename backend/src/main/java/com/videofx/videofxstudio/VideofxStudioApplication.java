package com.videofx.videofxstudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = "com.videofx.videofxstudio")
@CrossOrigin(origins = "http://localhost:3000")
@ComponentScan(basePackages = "com.videofx.videofxstudio.controller")

public class VideofxStudioApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideofxStudioApplication.class, args);
    }
}