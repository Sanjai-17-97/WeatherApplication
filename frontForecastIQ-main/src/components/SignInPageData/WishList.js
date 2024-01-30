import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, useNavigate } from 'react-router-dom';
import './weather.css';
import axios from 'axios';

const WishList = () => {
  const [weathers, setWeather] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://ec2-35-166-121-56.us-west-2.compute.amazonaws.com:8082/api/auth/get/${localStorage.getItem("email")}`)
      .then(response => {
        const processData = response.data.map(item => ({
          id: item.id,
          city: item.city,
          temperature: item.temperature.replace(/'/g, '').replace('"', ''),
          weather: item.weather.replace(/'/g, '')
        }))
        setWeather(processData)
      })

  }, [weathers])

  const handlelog = () => {
    localStorage.removeItem('token')
    navigate('/');
  }

  // *** DELETE BY EMAIL AND CITY ***
  const deleteWeather = (city) => {
    axios.delete(`http://ec2-35-166-121-56.us-west-2.compute.amazonaws.com:8082/api/auth/delete/${localStorage.getItem("email")}/${city}`)
  }

  return (
    <div>
      <div className="Nav" >
        <ul>
          <li><Link to="/weather">Home</Link></li>
          <li><Link to="/wish">WishList</Link></li>
          <li><Link to="/" onClick={handlelog}>Logout</Link></li>
        </ul>
    
      <div >
        <div className='two'>
          {weathers.map(weather => (
            <div className='three'>
              <h2>City:{weather.city}</h2>
              <h5>Temperature:{weather.temperature}</h5>
              <p>Weather:{weather.weather}</p>
              <button type="button" className="delete-button" onClick={() => deleteWeather(weather.city)}>Delete</button>
            </div>
          ))}
        </div>
      </div>
    </div>
    </div>

  );
}

export default WishList
