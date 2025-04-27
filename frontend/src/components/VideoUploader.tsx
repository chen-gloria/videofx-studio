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
      const formData = new FormData();
      formData.append("file", selectedFile);
      
      console.log(selectedFile);

      try {
          setUploadStatus("Uploading...");
          const response = await axios.post("/api/videos/upload", formData, {
                  headers: {
                      "Content-Type": "multipart/form-data",  // Make sure to set this header for file upload
                  }
              }
          );
          setUploadStatus(`Upload complete: ${response.data}`);
      } catch (error) {
          console.error("Error:", error);
          setUploadStatus("Upload failed. Try again." + error);
      }
  };


  return (
    <div className="flex flex-col items-center justify-center p-4 space-y-4">
      {/* 文件选择后预览 */}
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

      {/* 上传状态 */}
      {uploadStatus && <p className="mt-2">{uploadStatus}</p>}

      {/* 隐藏的 input 文件选择 */}
      <input
        type="file"
        ref={inputRef}
        onChange={handleFileChange}
        style={{ display: "none" }}  // 不显示在页面上
        accept="video/*"  // 仅允许视频文件
      />
    </div>
  );
};

export default VideoUploader;
