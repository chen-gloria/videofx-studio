package com.videofx.videofxstudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {

    private static final Logger logger = Logger.getLogger(VideoController.class.getName()); // Using java.util.logging.Logger

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        logger.info("Received file: " + file.getOriginalFilename());

        if (file.isEmpty()) {
            logger.warning("No file uploaded.");
            return ResponseEntity.badRequest().body("Please select a file");
        }

        try {
            // Save the file to a directory (make sure you set the correct directory path)
            String uploadDir = "/path/to/upload/directory/";
            File destinationFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(destinationFile); // Save the file to the specified directory

            logger.info("File successfully uploaded to: " + destinationFile.getAbsolutePath());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            logger.severe("Error during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file.");
        }
    }
}
