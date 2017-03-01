package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.Model.Weather;
import com.example.dfreeman.weatherapplicationtdd.View.LocationActivity;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")

public class ControllerUnitTest {

    private Controller controller;
    private LocationActivity locationActivity;
    private static final double DELTA = 1e-2;

    @Before
    public void setUp() {
        locationActivity = Robolectric.buildActivity(LocationActivity.class).create().get();
        controller = new Controller();
    }

    @Test
    public void testLocation() {
        String test;
        controller.setLocation(locationActivity, "0");
        test = controller.getLocation(locationActivity);
        assertEquals("0", test);
        controller.setLocation(locationActivity, "48315");
        test = controller.getLocation(locationActivity);
        assertEquals("48315", test);
    }

    @Test
    public void testCachedLocation() {
        String test;
        controller.setCachedLocation("0");
        test = controller.getCachedLocation();
        assertEquals("0", test);
        controller.setCachedLocation("48315");
        test = controller.getCachedLocation();
        assertEquals("48315", test);
    }

    @Test
    public void testCheckValid() {
        assertEquals(true, controller.isValid("48315"));
        assertEquals(false, controller.isValid("321"));
        assertEquals(false, controller.isValid("abc"));
    }

    @Test
    public void testGetWeather() throws JSONException {
        String data = "{\"coord\":{\"lon\":-83.03,\"lat\":42.67},\"weather\":[{\"id\":701,\"main" +
        "\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"},{\"id\":500,\"main" +
        "\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"},{\"id\":300,\"main" +
        "\":\"Drizzle\",\"description\":\"light intensity drizzle\",\"icon\":\"09d\"}]," +
        "\"base\":\"stations\",\"main\":{\"temp\":278.15,\"pressure\":1017,\"humidity" +
        "\":93,\"temp_min\":277.15,\"temp_max\":279.15},\"visibility\":4828,\"wind\":{" +
        "\"speed\":2.6,\"deg\":130},\"clouds\":{\"all\":90},\"dt\":1488291480,\"sys\":{\"type" +
        "\":1,\"id\":1460,\"message\":0.0052,\"country\":\"US\",\"sunrise\":1488283677," +
        "\"sunset\":1488324095},\"id\":5009586,\"name\":\"Shelby\",\"cod\":200}";
        Weather weather = controller.getWeather(data);
        assertEquals("Shelby", weather.location.getCity());
        assertEquals("US", weather.location.getCountry());
        assertEquals(5, Math.round((weather.temperature.getTemp() - 273.15)));
        assertEquals(93.0, weather.currentCondition.getHumidity(), DELTA);
        assertEquals(1017.0, weather.currentCondition.getPressure(), DELTA);
        assertEquals(2.59, weather.wind.getSpeed(), DELTA);
        assertEquals(130.0, weather.wind.getDeg(), DELTA);
    }

    @Test
    public void testGetWeeklyWeather() throws JSONException {
        String data = "{\"city\":{\"id\":5009586,\"name\":\"Shelby\",\"coord\":{\"lon\":-83.033,\"lat\":42.6709},\"country\":\"US\",\"population\":74099},\"cod\":\"200\",\"message\":10.7832584,\"cnt\":7,\"list\":[{\"dt\":1488387600,\"temp\":{\"day\":285.44,\"min\":270.78,\"max\":285.44,\"night\":270.78,\"eve\":277.01,\"morn\":284.06},\"pressure\":984.36,\"humidity\":79,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":6.81,\"deg\":222,\"clouds\":92,\"rain\":0.69,\"snow\":0.26},{\"dt\":1488474000,\"temp\":{\"day\":272.24,\"min\":267.73,\"max\":272.31,\"night\":269.49,\"eve\":271.22,\"morn\":267.73},\"pressure\":1008.68,\"humidity\":93,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":4.81,\"deg\":302,\"clouds\":68,\"snow\":0.56},{\"dt\":1488560400,\"temp\":{\"day\":270.63,\"min\":264.25,\"max\":270.83,\"night\":264.25,\"eve\":269.14,\"morn\":265.01},\"pressure\":1018.77,\"humidity\":90,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":5.66,\"deg\":319,\"clouds\":44,\"snow\":0.06},{\"dt\":1488646800,\"temp\":{\"day\":275.49,\"min\":266.05,\"max\":275.49,\"night\":271.56,\"eve\":274.35,\"morn\":266.05},\"pressure\":1016.97,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":4.09,\"deg\":133,\"clouds\":42,\"snow\":0.02},{\"dt\":1488733200,\"temp\":{\"day\":283.3,\"min\":272.51,\"max\":283.3,\"night\":279.1,\"eve\":281.42,\"morn\":272.51},\"pressure\":1008.92,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":4.15,\"deg\":189,\"clouds\":21},{\"dt\":1488819600,\"temp\":{\"day\":282.41,\"min\":273,\"max\":282.41,\"night\":273,\"eve\":279.68,\"morn\":280.23},\"pressure\":996.88,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":5.58,\"deg\":231,\"clouds\":87,\"rain\":2.11,\"snow\":0.02},{\"dt\":1488906000,\"temp\":{\"day\":274.32,\"min\":268.82,\"max\":274.32,\"night\":272.09,\"eve\":273.93,\"morn\":268.82},\"pressure\":1006.33,\"humidity\":0,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":1.5,\"deg\":321,\"clouds\":94,\"snow\":1.81}]}";
        Weather[] weather = controller.getWeeklyWeather(data);
        assertEquals(12, Math.round((weather[0].temperature.getTemp() - 273.15)));
        assertEquals(weather[1].currentCondition.getCondition(), "Snow");
        assertEquals(weather[2].currentCondition.getDescr(), "light snow");
        assertEquals(weather[3].currentCondition.getCondition(), "Clear");
        assertEquals(10, Math.round((weather[4].temperature.getTemp() - 273.15)));
    }

    @Test
    public void testIsWeatherBad() {
        String test1 = "Clear";
        String test2 = "Snow";
        assertEquals(controller.isWeatherBad(test1), false);
        assertEquals(controller.isWeatherBad(test2), true);
    }
}
