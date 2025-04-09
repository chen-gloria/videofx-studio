package om.videofx.videofx_backend;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            result.put("filename", file.getOriginalFilename());
            result.put("size", file.getSize());
            // You can add more logic here, like using ffmpeg to extract metadata
        } catch (Exception e) {
            result.put("error", "File upload failed");
        }

        return ResponseEntity.ok(result);
    }
}
