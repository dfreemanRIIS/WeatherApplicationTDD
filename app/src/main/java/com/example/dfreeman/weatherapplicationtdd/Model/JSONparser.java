package com.example.dfreeman.weatherapplicationtdd.Model;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONParser {

    public static Weather getWeather(String data) throws JSONException  {
        Weather weather = new Weather();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));
        weather.location = loc;

        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
        weather.currentCondition.setDescr(getString("description", JSONWeather));
        weather.currentCondition.setCondition(getString("main", JSONWeather));
        weather.currentCondition.setIcon(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        weather.currentCondition.setHumidity(getInt("humidity", mainObj));
        weather.currentCondition.setPressure(getInt("pressure", mainObj));
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
        weather.temperature.setTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        weather.wind.setSpeed(getFloat("speed", wObj));
        weather.wind.setDeg(getFloat("deg", wObj));

        // Clouds
        JSONObject cObj = getObject("clouds", jObj);
        weather.clouds.setPerc(getInt("all", cObj));

        return weather;
    }

    public static Weather[] getWeeklyWeather(String data) throws JSONException  {
        Weather[] weather = new Weather[5];
        JSONObject jsonObj = new JSONObject(data);
        JSONArray list=jsonObj.getJSONArray("list");

        for (int i = 0; i <5; i++) {
            weather[i] = new Weather();
            JSONObject item = list.getJSONObject(i);

            JSONObject temp = item.getJSONObject("temp");
            String temp2 = temp.getString("max");
            Float temp3 = Float.parseFloat(temp2);
            weather[i].temperature.setTemp(temp3);

            JSONArray currentCondition = item.getJSONArray("weather");
            JSONObject jObj = currentCondition.getJSONObject(0);
            String currentCondition2 = jObj.getString("main");
            weather[i].currentCondition.setCondition(currentCondition2);

            JSONArray desc = item.getJSONArray("weather");
            JSONObject jObj2 = desc.getJSONObject(0);
            String desc2 = jObj2.getString("description");
            weather[i].currentCondition.setDescr(desc2);
        }

        return weather;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
