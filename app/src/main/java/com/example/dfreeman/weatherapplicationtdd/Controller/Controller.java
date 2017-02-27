package com.example.dfreeman.weatherapplicationtdd.Controller;

import android.content.Context;

import com.example.dfreeman.weatherapplicationtdd.Model.ApiHelper;
import com.example.dfreeman.weatherapplicationtdd.Model.JSONParser;
import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class Controller {

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

    public String getTemp(String location, int month, int day) throws JSONException {
        ApiHelper apiHelper = new ApiHelper();
        JSONObject jo = apiHelper.getWeather();
        JSONParser jsonparser = new JSONParser();
        return jsonparser.todaysWeather(location, month, day, jo);
    }

}
