import React, { useState } from "react";
import axios from "axios";
import { useDropzone } from "react-dropzone";
import { motion } from "framer-motion";  // For smooth animations

const VideoUploader = () => {
  const [file, setFile] = useState<File | null>(null);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadStatus, setUploadStatus] = useState("");

  // Dropzone handler for file selection
  const { getRootProps, getInputProps } = useDropzone({
    accept: "video/*",  // Accept only video files
    onDrop: (acceptedFiles) => {
      setFile(acceptedFiles[0]);
    }
  });

  // Handle file upload
  const handleUpload = async () => {
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    try {
      setUploadStatus("Uploading...");
      const response = await axios.post(
        "http://localhost:8080/api/videos/upload",  // Backend endpoint
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          },
          onUploadProgress: (progressEvent: any) => {
            const progress = Math.round((progressEvent.loaded * 100) / progressEvent.total);
            setUploadProgress(progress);
          }
        }
      );

      setUploadStatus(`Upload complete: ${response.data}`);
    } catch (error) {
      setUploadStatus("Upload failed. Try again.");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center p-4 space-y-4">
      <div
        {...getRootProps()}
        className="border-2 border-dashed p-4 rounded-md w-full max-w-md text-center"
      >
        <input {...getInputProps()} />
        <p>Drag & Drop your video here, or click to select</p>
      </div>

      {file && (
        <div className="mt-2">
          <p>Selected File: {file.name}</p>
        </div>
      )}

      {uploadProgress > 0 && (
        <motion.div
          className="w-full bg-gray-300 h-2 rounded-md mt-4"
          style={{ width: `${uploadProgress}%` }}
          initial={{ width: 0 }}
          animate={{ width: `${uploadProgress}%` }}
          transition={{ duration: 0.5 }}
        ></motion.div>
      )}

      <button
        onClick={handleUpload}
        className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-md"
      >
        Upload Video
      </button>

      {uploadStatus && <p className="mt-2">{uploadStatus}</p>}
    </div>
  );
};

export default VideoUploader;