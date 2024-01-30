import React from 'react';
import UserRegistrationForm from './components/LoginPage/UserRegistrationForm.js';
import './components/LoginPage/UserRegistrationForm.css';
import Login from './components/LoginPage/Login.js';




import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Weather from './components/SignInPageData/Weather.js';
import WishList from './components/SignInPageData/WishList.js';
import LandingPage from './components/LoginPage/LandingPage.js';


function App() {
  
  return (
    <div className="App">
  <Router>
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/UserRegistrationForm" element={<UserRegistrationForm/>} />
        <Route path="/weather" element={<Weather/>} />
        <Route path="/wish" element={<WishList/>} />
        
      </Routes>
    </Router>
   
    </div>
  );
}

export default App;
