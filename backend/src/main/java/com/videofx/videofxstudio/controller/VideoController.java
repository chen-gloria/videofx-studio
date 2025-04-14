package com.videofx.videofxstudio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @GetMapping("/test")
    public ResponseEntity<String> getTestVideo() {
        return ResponseEntity.ok("Test successfulaaaa");
    }
    
    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        // 先简单返回文件名，后续加入逻辑保存视频和创建任务
        return "Received video: " + file.getOriginalFilename();
    }
}
