# VideoFX Studio App

VideoFX Studio is a video compositing and export web application powered by WASM. It allows users to upload, process, and export videos using a backend built with Spring Boot and provides a RESTful API for video-related operations. The application is built with an emphasis on performance, scalability, and a seamless user experience across web and mobile platforms, making it a great fit for video compositing.

# MVP Features

| **Feature** | **Description** | **Tech Stack** |
|-------------|-----------------|----------------|
| **Frontend Upload Interface** | Drag-and-drop or select video file upload. Preview display, status indicator, and upload progress bar. | React + TypeScript + Tailwind CSS<br>Libraries: `react-dropzone`, `shadcn/ui`, `framer-motion` |
| **Backend Task Coordination Service** | Handles the entire video pipeline: upload, analysis, editing, and preview download. Manages asynchronous tasks, status polling, result storage, and CDN link management. | Java (Spring Boot), Redis (task queue), PostgreSQL (task metadata) |
| **AI Editing Logic** | Automates video processing, including:<br>- Removing silent segments<br>- Rhythm-based cutting<br>- Auto-transitions<br>- Subtitle generation using Whisper | Python (MoviePy, FFmpeg, Whisper)<br>Future Expansion: Rust/C++ + WASM for enhanced video editing and subtitle rendering. |
| **Platform Ratio Auto-Adapting and Export** | Automatically adapts videos for different platforms (e.g., TikTok, Bilibili, Instagram). Outputs various resolutions (e.g., 9:16, 1:1). Exports videos to cloud storage (e.g., Cloudflare R2, S3-compatible) with CDN support. | FFmpeg, custom export logic |
| **Video Preview Player** | Online preview of generated videos. Direct download link for the final video. | HTML5 Video + Video.js or custom UI<br>Future Enhancement: WASM-based video preview enhancements (e.g., Seeker). |


# Key Technical Areas Aligned with Video Compositing & Editing

1. **Video Compositing & Performance**: Designed with video performance and compositing in mind. Leveraging **FFmpeg** for video processing and **WASM** for accelerated video previews ensures smooth, high-performance editing and playback experiences.

2. **Fullstack Development**: From video upload and task coordination to **AI-based** video editing, platform export, and video preview, the app covers the entire fullstack development pipeline. It also includes integrations for **cloud storage and CDN** for fast content delivery.

3. Scalable Backend: Built with **Spring Boot**, **Redis**, and **PostgreSQL** to ensure scalability and efficient task management for video editing pipelines.

4. Video Editing Logic: Implements **AI-based** video editing features like auto-transitions and subtitle generation, with room for expansion into more advanced editing logic using **Rust/C++ and WASM**.


# How to run

This project uses Docker Compose to run the full-stack app (backend + frontend + Redis) for development.

## Project structure
```bash
/backend      # Java Spring Boot application
/frontend     # ReactJS frontend (using Vite)
docker-compose.yml
```

## Running the App

### 1. Start All Services
```bash
docker compose up
```

This will:

- Build and run the backend (Java Spring Boot) on http://localhost:8080
- Run the frontend (React + Vite dev server) on http://localhost:3000
- Start a Redis server on localhost:6379

### 2. Access the App

| Service | URL |
| ------ | ------ |
| Frontend | http://localhost:3000 |
| Backend | http://localhost:8080 | 
| Redis | localhost:6379 (internal access) |

### 3. Development Notes
- Frontend code changes will hot reload automatically via Vite.

- Backend code changes will auto-restart thanks to spring-boot-devtools.