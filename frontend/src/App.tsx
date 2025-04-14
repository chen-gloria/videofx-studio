import { useState } from 'react';
import UploadVideo from './components/VideoUploader';
import './App.css'

const App: React.FC = () => {
  return (
    <div className="App">
      <h1 className="text-center text-3xl mt-10">AI Video Editing Assistant</h1>
      <UploadVideo />
    </div>
  );
};

export default App
