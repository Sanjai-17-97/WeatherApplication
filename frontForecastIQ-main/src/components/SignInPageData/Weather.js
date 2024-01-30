import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './weather.css';
import axios from 'axios';
import WishList from './WishList';
const Weather = ({ value }) => {
  const [city, setCity] = useState('');
  const [weathers, setWeather] = useState({
    temperature: "",
    city: "",
    country: "",
    weather: ""
  });
  const [flag,setFlag] = useState(false);

  const navigate = useNavigate();

  const apikey = 'ed8fa2a7e21d5b6911be91ff486262e6';
  const email = localStorage.getItem("email");
  const token = 'received token';
 
  localStorage.setItem('token', token);
  localStorage.setItem('mail', email);

  const headers = {
    'Authorization': `Bearer
      ${localStorage.getItem('token')},
      ${localStorage.getItem('mail')}
      `};
 
  const handlelog = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('mail');

    navigate('/');
  }
  const search = () => {
    if (city === '') return;
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&APPID=${apikey}`;
    fetch(apiUrl)
      .then(response => response.json())
      .then(data => {
        setWeather({
          temperature: data.main.temp,
          city: data.name,
          country: data.sys.country,
          weather: data.weather[0].description,
        })

        setFlag(true);
      })


      .catch(error => {
        console.log('Error Fetching Weather Data:', error)
      });
  }

  //Add to wishlist
  const add = (e) => {
    e.preventDefault();
    alert("City Added to the Wishlist");
    const { temperature, city, weather } = weathers;
    axios
      .post(`http://ec2-35-166-121-56.us-west-2.compute.amazonaws.com:8082/api/auth/wish/${email}`, { params: email, city, temperature, weather, headers })
      .then((response) => (response.data)
      )

  }

  const displayWeatherOfCity = (
    <div className='result'>
      <h2>Weather in {weathers.city},{weathers.country}</h2>
      <p>Temperature in {weathers.temperature} C</p>
      <p>Weather:{weathers.weather}</p>
      <button onClick={add} className='button-34'>Add to WishList</button>
    </div>
  )

  return (
    <div>
      <div className="Nav">
        <ul>
          <li><Link to="/weather">Home</Link></li>
          <li><Link to="/wish">Wishlist</Link></li>
          <li><Link to="/" onClick={handlelog}>Logout</Link></li>
        </ul>
        <p>{value}</p>

        <div className="searchForm">
          <label htmlFor="cityInput">Enter City:</label>
          <input
            type="text"
            id="cityInput"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            className="form-control"
            required
          />
          <button onClick={search} className="button-62">Search</button>
        </div>
        {flag && displayWeatherOfCity}
        <Link to={"/wish"} element={<WishList />} />
      </div>


    </div>


  )
}

export default Weather
