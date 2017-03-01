package com.example.dfreeman.weatherapplicationtdd.Model;

public class WeatherConditionCheck {

    public boolean isWeatherBad(String weather) {
        if(weather.equals("Thunderstorm")) {
            return true;
        }
        if(weather.equals("Snow")) {
            return true;
        }
        if(weather.equals("Atmosphere")) {
            return true;
        }
        if(weather.equals("Extreme")) {
            return true;
        }
        return false;
    }
}
