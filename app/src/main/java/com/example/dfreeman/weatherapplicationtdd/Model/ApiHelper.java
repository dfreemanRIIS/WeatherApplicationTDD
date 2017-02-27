package com.example.dfreeman.weatherapplicationtdd.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiHelper {
    public JSONObject getWeather() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("month", "1");
        jo.put("day", "1");
        jo.put("temp", "60");
        return jo;
    }
}
