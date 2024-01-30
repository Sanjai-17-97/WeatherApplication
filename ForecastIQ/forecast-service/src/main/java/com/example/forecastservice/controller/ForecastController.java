package com.example.forecastservice.controller;

import com.example.forecastservice.model.Weather;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    Logger log = LoggerFactory.getLogger(ForecastController.class);


    RestTemplate restTemplate = new RestTemplate();

    //add your api key here
    private static final String API_KEY = "d00d195be0f1f00c2913bed066ddd18e";

    //add the base api url here
    private static final String API_URL = "http://api.weatherstack.com/current";

    // get weather forecast of the city
    @GetMapping("/weather/{city}")
    public Weather cityWeather(@PathVariable String city) {
        System.out.println("city ==> " + city);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("query", city);
        String urlWithParams = builder.toUriString();
        ResponseEntity<Weather> responseEntity = restTemplate.exchange(urlWithParams, HttpMethod.GET, null, Weather.class);
        return responseEntity.getBody();
    }
}
/*

 @GetMapping("/weather")
    public ResponseEntity<?> cityWeather(@RequestBody Forecast forecast){

        String city = forecast.getCity();
        String key = forecast.getKey();
        String uri = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}";

        return restTemplate.exchange(uri, HttpMethod.GET,null, Object.class, city,key);
    }
 */