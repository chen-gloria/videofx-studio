package com.videofx.videofxstudio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String ALLOWED_ORIGIN = "http://localhost:3000";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOrigins(ALLOWED_ORIGIN)  // Your React app's URL
                .allowedMethods("*")  // Allow all HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow cookies/auth
                .maxAge(3600);  // Cache preflight requests for 1 hour
    }
}