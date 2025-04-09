# VideoFX Studio App

VideoFX Studio is a simple web application that allows users to upload and process videos. It uses a backend built with **Spring Boot** and provides an API for handling video uploads, as well as interacting with various video processing features.

## Features

- **Video Upload**: Users can upload video files (e.g., MP4, AVI, MOV) via a simple REST API.
- **Video Processing**: Placeholder for video processing functionalities (e.g., encoding, extracting metadata).
- **API**: RESTful endpoints for video-related operations.

## Prerequisites

Before running this project, make sure you have the following tools installed:

- **Java 17+**: [Download Java 17](https://openjdk.java.net/)
- **Maven**: [Install Maven](https://maven.apache.org/install.html)
- **Optional**: Docker (if you want to run the app inside a container)

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/chen-gloria/videofx-studio.git
cd videofx-studio
```

### 2. Install Java & Maven

If you haven't already installed Java 17 and Maven, follow the instructions below:

- Install Java 17 (via Homebrew on macOS):

```bash
brew install openjdk@17
```

- Install Maven:

```bash
brew install maven
```

### 3. Build the Project

To build the project and install all dependencies, run:

```bash
mvn clean package
```

### 4. Run the Backend

Start the backend server using Maven:

```bash
mvn spring-boot:run
```

This will run the Spring Boot application, and the backend will be accessible at `http://localhost:8080`.


## API Endpoints