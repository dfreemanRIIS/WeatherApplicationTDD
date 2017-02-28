package com.example.dfreeman.weatherapplicationtdd.Model;

import java.io.Serializable;

public class Location implements Serializable {

    private float longitude;
    private float latitude;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
