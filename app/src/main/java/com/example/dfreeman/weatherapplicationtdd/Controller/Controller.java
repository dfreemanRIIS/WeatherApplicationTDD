package com.example.dfreeman.weatherapplicationtdd.Controller;

import android.content.Context;

import com.example.dfreeman.weatherapplicationtdd.Model.ApiHelper;
import com.example.dfreeman.weatherapplicationtdd.Model.JSONParser;
import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;
import com.example.dfreeman.weatherapplicationtdd.Model.Weather;

import org.json.JSONException;
import org.json.JSONObject;

public class Controller {

    private String cachedLocation = "48315";
    private PreferenceHelper preferenceHelper;

    public Controller() {
        preferenceHelper = new PreferenceHelper();
    }

    public void setLocation(Context context, String location) {
        preferenceHelper.save(context, location);
    }

    public String getLocation(Context context) {
        return preferenceHelper.getValue(context);
    }

    public boolean isValid(String location) {
        return preferenceHelper.isValid(location);
    }

    public String getCachedLocation() {
        return cachedLocation;
    }

    public void setCachedLocation(String cachedLocation) {
        this.cachedLocation = cachedLocation;
    }

    public String getData(String params) {
        String data = ((new ApiHelper()).getWeatherData(params));
        return data;
    }

    public Weather getWeather(String data) throws JSONException {
        return JSONParser.getWeather(data);
    }

}
