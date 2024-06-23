import logo from './logo.svg';
import './App.css';
import MainLayout from './features/layout';
import background from './assets/images/backgroundimg.jpg';

function App() {
  return (
    <div style={{ backgroundImage: `url(${background})` }}>
      <MainLayout></MainLayout>
    </div>
  );
}

export default App;
