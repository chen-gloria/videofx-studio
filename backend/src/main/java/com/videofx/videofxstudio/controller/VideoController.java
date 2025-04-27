package com.videofx.videofxstudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.videofx.videofxstudio.service.VideoProcessingService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class VideoController {

    private static final Logger logger = Logger.getLogger(VideoController.class.getName()); // Using java.util.logging.Logger

    @Autowired
    private VideoProcessingService videoProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        logger.info("Received file: " + file.getOriginalFilename());

        if (file.isEmpty()) {
            logger.warning("No file uploaded.");
            return ResponseEntity.badRequest().body("Please select a file");
        }

        try {
            // Store file to local
            String uploadDirPath = new File(System.getProperty("user.dir"), "uploads").getAbsolutePath();
            File uploadDir = new File(uploadDirPath);
            
            // If the "uploads" folder doesn't exist, create it
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                if (created) {
                    logger.info("Uploads directory created at: " + uploadDirPath);
                } else {
                    logger.warning("Failed to create uploads directory at: " + uploadDirPath);
                }
            } else {
                logger.info("Uploads directory already exists at: " + uploadDirPath);
            }

            File destinationFile = new File(uploadDir, file.getOriginalFilename());

            file.transferTo(destinationFile);

            logger.info("File stored to local.");

            // 生成唯一任务 ID
            String taskId = UUID.randomUUID().toString();

            logger.info("Generated UUID." + taskId);
    
            // 将任务添加到 Redis 队列
            videoProcessingService.addTaskToQueue(taskId, destinationFile.getAbsolutePath());
    
            logger.info("File successfully uploaded and task added to queue: " + destinationFile.getAbsolutePath());
            return ResponseEntity.ok("File uploaded successfully and task added to queue.");
        } catch (IOException e) {
            logger.severe("Error during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file.");
        }
    }
}
