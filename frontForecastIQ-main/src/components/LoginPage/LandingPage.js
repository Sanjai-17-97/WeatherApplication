
import React from "react";
import { Link } from "react-router-dom";
import './UserRegistrationForm.css';
import './login.css';
const LandingPage=()=>{



    return (
      <div>
        <div>
            
            <nav class="navbar navbar-light bg-info">
                <a class="navbar-brand" href="#">
                    <img src="logo.png" width="30" height="30" alt=""/>
                    <span>ForecastIQ</span>
                </a>
                <form class="form-inline">
                    <Link class="btn btn-outline-success" to="/Login">Login</Link>
                    <Link class="btn btn-outline-success" to="/UserRegistrationForm">Register</Link>
                </form>
            </nav>
        </div>
        <div className="landingpage-body">
            <div className="sliding-text-container">
                <div className="sliding-text">Welcome to ForecastIQ Login / Register for weather updates!</div>
            </div>
        </div>
        {/* <footer className="footer bg-dark text-light">
          <div className="container">
            <div className="row">
                <div className="col-md-6">
                    <p>&copy; 2023 ForecastIQ. All Rights reserved.</p>
                </div>
                <div className="col-md-6 text-right">
                    <a target="_blank" rel="noopener no referrer">
                        <i className="fab fa-facebook-f"></i>
                    </a>
                </div>
            </div>
          </div>
        </footer> */}
    </div>
    
    );
}
export default LandingPage;