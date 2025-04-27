package com.videofx.videofxstudio.config;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTestConfig {

    // Mock the RedisTemplate bean for testing
    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        return Mockito.mock(RedisTemplate.class);  // Explicitly mock the RedisTemplate
    }
}
