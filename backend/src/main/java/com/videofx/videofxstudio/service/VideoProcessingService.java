package com.videofx.videofxstudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class VideoProcessingService {

    private static final Logger logger = Logger.getLogger(VideoProcessingService.class.getName());

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public VideoProcessingService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String TASK_QUEUE_KEY = "video-processing-queue";  // Redis Queue for tasks
    private static final String TASK_STATUS_KEY = "video-task-status:";  // Redis Task Status

    // Add task to Redis queue
    public void addTaskToQueue(String taskId, String videoPath) {
        // Store task ID and video path in Redis queue
        redisTemplate.opsForList().leftPush(TASK_QUEUE_KEY, taskId);

        // Store task status and path
        redisTemplate.opsForHash().put("task:" + taskId, "status", "queued");
        redisTemplate.opsForHash().put("task:" + taskId, "videoPath", videoPath);
    }

    private void setTaskStatus(String taskId, String status) {
        redisTemplate.opsForValue().set(TASK_STATUS_KEY + taskId, status, 24, TimeUnit.HOURS);
    }

    @Async
    public void processVideo(String videoFilePath, String taskId) {
        // Simulate video processing (e.g., remove silence, apply effects, etc.)
        try {
            // In reality, you'd process the video here (using the FFmpeg logic or other services)
            removeSilence(videoFilePath, "/path/to/output/" + taskId + "_processed.mp4");

            // After processing, set status to completed
            setTaskStatus(taskId, "Completed");

            // Optionally store the processed file path in Redis for reference
            storeProcessedFileInRedis(taskId, "/path/to/output/" + taskId + "_processed.mp4");

        } catch (IOException e) {
            // In case of error, set status to Failed
            setTaskStatus(taskId, "Failed");
            logger.severe("Error during video processing for task: " + taskId);
        }
    }

    private void removeSilence(String inputFile, String outputFile) throws IOException {
        // FFmpeg command to remove silence
        String command = "ffmpeg -i " + inputFile + " -af silenceremove=1:0:-50dB " + outputFile;
        Process process = new ProcessBuilder(command.split(" ")).start();

        try {
            process.waitFor();
            logger.info("Successfully removed silence from: " + inputFile);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IOException("Error during silence removal");
        }
    }

    private void storeProcessedFileInRedis(String taskId, String outputFile) {
        redisTemplate.opsForValue().set("video-output:" + taskId, outputFile);
    }
}
