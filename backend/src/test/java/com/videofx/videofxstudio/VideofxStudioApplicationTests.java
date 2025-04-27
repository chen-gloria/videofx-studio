package com.videofx.videofxstudio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.videofx.videofxstudio.controller.VideoController;
import com.videofx.videofxstudio.service.VideoProcessingService;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Use MockitoExtension for mocking in JUnit 5
class VideofxStudioApplicationTests {

    @InjectMocks
    private VideoController videoController;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private VideoProcessingService videoProcessingService;

    @Test
    void contextLoads() {
        // Test passes if Spring context loads
    }
}
