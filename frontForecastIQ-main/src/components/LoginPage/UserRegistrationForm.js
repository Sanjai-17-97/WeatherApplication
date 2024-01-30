import React, { useState } from 'react';
import axios from 'axios';
import './UserRegistrationForm.css';

const UserRegistrationForm = () => {
  

  const [user, setUser] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    contactNumber: '',
  });
  const handleChange = (event) => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post('http://ec2-35-166-121-56.us-west-2.compute.amazonaws.com:8082/api/auth/reg', {...user})
      .then((response) => {
        console.log(response.data);
        alert("Registration Successful!");
        // Handle success response here
        window.location.href = 'Login';
        
      })
  };

  return (
    <div>
      <nav class="navbar navbar-light bg-info">
                <a class="navbar-brand" href="#">
                    <img src="logo.png" width="30" height="30" alt=""/>
                    <span>ForecastIQ</span>
                </a>
                {/* <form class="form-inline">
                    <Link class="btn btn-outline-success" to="/login">Login</Link>
                </form> */}
            </nav>
    <form className="user-registration-form" onSubmit={handleSubmit}>
      <div className="form-group">
      </div>
      <div className="form-group">
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          className="form-control"
          id="firstName"
          name="firstName"
          value={user.firstName}
          onChange={handleChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="lastName">Last Name:</label>
        <input
          type="text"
          className="form-control"
          id="lastName"
          name="lastName"
          value={user.lastName}
          onChange={handleChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          className="form-control"
          id="email"
          name="email"
          value={user.email}
          onChange={handleChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          className="form-control"
          id="password"
          name="password"
          value={user.password}
          onChange={handleChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          className="form-control"
          id="confirmPassword"
          name="confirmPassword"
          value={user.confirmPassword}
          onChange={handleChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="contactNumber">Contact Number:</label>
        <input
          type="text"
          className="form-control"
          id="contactNumber"
          name="contactNumber"
          value={user.contactNumber}
          onChange={handleChange}
        />
      </div>
      <button type="submit" className="button-34 btn-primary">Register</button>
      
    </form>
    </div>
  );
};

export default UserRegistrationForm;
