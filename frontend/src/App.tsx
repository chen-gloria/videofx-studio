import VideoUploader from './components/VideoUploader';
import './App.css'

const App: React.FC = () => {
  return (
    <div className="App">
      <h1 className="text-center text-3xl mt-10">AI Video Editing Assistant</h1>
      <VideoUploader />
    </div>
  );
};

export default App
