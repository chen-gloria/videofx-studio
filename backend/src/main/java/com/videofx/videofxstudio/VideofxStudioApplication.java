package com.videofx.videofxstudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableAsync
@CrossOrigin(origins = "*")
@SpringBootApplication(scanBasePackages = "com.videofx.videofxstudio")
@ComponentScan(basePackages = "com.videofx.videofxstudio")
public class VideofxStudioApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideofxStudioApplication.class, args);
    }
}