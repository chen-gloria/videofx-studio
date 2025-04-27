import React, { useState, useRef } from "react";
import axios from "axios";

const VideoUploader = () => {
  const [file, setFile] = useState<File | null>(null);
  const [uploadStatus, setUploadStatus] = useState("");

  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleOpenFileDialog = () => {
    inputRef.current?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
      handleUpload(selectedFile);
    }
  };

  const handleUpload = async (selectedFile: File) => {
    if (!selectedFile) {
      setUploadStatus("No file selected");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);
    
    console.log(formData.getAll);

    try {
        setUploadStatus("Uploading...");
        const response = await axios.post("/api/videos/upload", formData, {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          }
        );
        setUploadStatus(`Upload complete: ${response.data}`);
    } catch (error) {
      let errorMessage = "Upload failed";
    
      if (axios.isAxiosError(error)) {
        if (error.code === "ERR_NETWORK") {
          errorMessage = "Cannot connect to server";
        } else if (error.response) {
          // Handle HTTP errors
          errorMessage = error.response.data?.message || 
            `Server error: ${error.response.status}`;
        }
      }
      
      setUploadStatus(errorMessage);
      console.error("Upload error:", error);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center p-4 space-y-4">
      {file && (
        <div className="mt-4 w-full max-w-md">
          <video
            src={URL.createObjectURL(file)}
            controls
            className="mt-2 w-full rounded-md"
            />
          <p>Selected File: {file.name}</p>
        </div>
      )}

      <button
        onClick={handleOpenFileDialog}
        className="bg-blue-500 text-white py-2 px-4 rounded-md"
      >
        Select and Upload Video
      </button>

      {uploadStatus && <p className="mt-2">{uploadStatus}</p>}

      <input
        type="file"
        ref={inputRef}
        onChange={handleFileChange}
        style={{ display: "none" }}
        accept="video/*"
      />
    </div>
  );
};

export default VideoUploader;
