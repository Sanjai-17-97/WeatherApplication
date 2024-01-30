package com.example.forecastservice.request;

public class Forecast {

    String city;
    String key;

    public Forecast() {

    }

    public Forecast(String city, String key) {
        this.city = city;
        this.key = key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
