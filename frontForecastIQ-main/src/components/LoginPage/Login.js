import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './UserRegistrationForm.css';
import { Link } from 'react-router-dom';
import Weather from '../SignInPageData/Weather';


const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [credentials, setCredentials] = useState({email:"",password:""});

  //handle function
  const handleChangeEmail = (event) => {
    (setEmail(event.target.value));
    console.log("Email", email);
  };

  const handleChangePassword = (event) => {
    (setPassword(event.target.value));
    console.log("Pass", password);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const token='received token';
    // const mail = 'Email Recieved';
    localStorage.setItem('token',token)
    localStorage.setItem('email',email)
    setCredentials ({ email:email, password:password });
    
    const headers={
      'Authorization':`Bearer
      ${localStorage.getItem('token')},
      ${localStorage.getItem('email')}
      `};
    if (email) {
      console.log("credentials",credentials);
      axios
        .post('http://ec2-35-166-121-56.us-west-2.compute.amazonaws.com:8082/api/auth/login', {  email, password ,headers })
        
        .then((response) => {
          console.log(response.data);
          // Handle success response here
          // Redirect to the addData page
          
          window.location.href = 'weather';
          
        })
        .catch((error) => {
          console.error(error.response.data);
          // Handle error here
          alert('Invalid login credentials');
        });
    } else {
      // Handle the case when loginId is missing
      console.error('EmailID is required');
    }
  };

  return (
    <div >
      <nav class="navbar navbar-light bg-info">
                <a class="navbar-brand" href="">
                    <img src="logo.png" width="30" height="30" alt=""/>
                    <span>ForecastIQ</span>
                </a>
                <form class="form-inline">
                    <Link class="btn btn-outline-success" to="/UserRegistrationForm">Register</Link>
                </form>
            </nav>
    <div className="login-container">
      <div className='loginpage-text'>
        <h1>Welcome to ForecastIQ</h1>
        <p>Login or Register</p>
        <p>
          Don't have an account? <Link to="/UserRegistrationForm">Click here to sign up</Link>
        </p>
      </div>
      <div className="login-form">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="loginId">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={email}
              onChange={handleChangeEmail}
              className="form-control"
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={handleChangePassword}
              className="form-control"
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Login
          </button>
        </form>
      </div>

    </div>
    <footer>
            <p>&copy; {new Date().getFullYear()} ForecastIQ</p>
          </footer>
          <Link to={"/weather"} element={<Weather />}/>
          
          
    </div>
  );
};

export default Login;
