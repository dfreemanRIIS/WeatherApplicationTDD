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
        String data = "{\"cod\":\"200\",\"message\":0.1488,\"cnt\":40,\"list\":[{\"dt" +
        "\":1488304800,\"main\":{\"temp\":282.36,\"temp_min\":279.533,\"temp_max" +
        "\":282.36,\"pressure\":1004.36,\"sea_level\":1028.35,\"grnd_level\":1004.36," +
        "\"humidity\":75,\"temp_kf\":2.83},\"weather\":[{\"id\":500,\"main\":\"Rain\"," +
        "\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":92}," +
        "\"wind\":{\"speed\":4.61,\"deg\":165},\"rain\":{\"3h\":0.5275},\"sys\":{\"pod" +
        "\":\"d\"},\"dt_txt\":\"2017-02-28 18:00:00\"},{\"dt\":1488315600,\"main\":{" +
        "\"temp\":283.18,\"temp_min\":281.059,\"temp_max\":283.18,\"pressure\":1000.68," +
        "\"sea_level\":1024.55,\"grnd_level\":1000.68,\"humidity\":79,\"temp_kf\":2.12}," +
        "\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon" +
        "\":\"10d\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":4.08,\"deg\":175.5},\"rain" +
        "\":{\"3h\":0.305},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-02-28 21:00:00\"},{\"dt" +
        "\":1488326400,\"main\":{\"temp\":283.09,\"temp_min\":281.674,\"temp_max\":283.09," +
        "\"pressure\":997.93,\"sea_level\":1021.78,\"grnd_level\":997.93,\"humidity\":83," +
        "\"temp_kf\":1.41},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":" +
        "\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed" +
        "\":4.37,\"deg\":178.002},\"rain\":{\"3h\":0.16},\"sys\":{\"pod\":\"n\"},\"dt_txt" +
        "\":\"2017-03-01 00:00:00\"},{\"dt\":1488337200,\"main\":{\"temp\":284.21,\"temp_min" +
        "\":283.498,\"temp_max\":284.21,\"pressure\":993.78,\"sea_level\":1017.44,\"grnd_level" +
        "\":993.78,\"humidity\":82,\"temp_kf\":0.71},\"weather\":[{\"id\":500,\"main\":\"Rain\"," +
        "\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{" +
        "\"speed\":6.48,\"deg\":185.001},\"rain\":{\"3h\":0.86},\"sys\":{\"pod\":\"n\"},\"dt_txt" +
        "\":\"2017-03-01 03:00:00\"},{\"dt\":1488348000,\"main\":{\"temp\":284.979,\"temp_min" +
        "\":284.979,\"temp_max\":284.979,\"pressure\":988.84,\"sea_level\":1012.43,\"grnd_level" +
        "\":988.84,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\"," +
        "\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{" +
        "\"speed\":7.03,\"deg\":197.007},\"rain\":{\"3h\":0.805},\"sys\":{\"pod\":\"n\"}," +
        "\"dt_txt\":\"2017-03-01 06:00:00\"},{\"dt\":1488358800,\"main\":{\"temp\":284.071," +
        "\"temp_min\":284.071,\"temp_max\":284.071,\"pressure\":986.54,\"sea_level\":1010.1," +
        "\"grnd_level\":986.54,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":501,\"main" +
        "\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all" +
        "\":80},\"wind\":{\"speed\":5.56,\"deg\":251.502},\"rain\":{\"3h\":10.725},\"sys\":{" +
        "\"pod\":\"n\"},\"dt_txt\":\"2017-03-01 09:00:00\"},{\"dt\":1488369600,\"main\":{\"temp" +
        "\":283.067,\"temp_min\":283.067,\"temp_max\":283.067,\"pressure\":987.64,\"sea_level" +
        "\":1011.23,\"grnd_level\":987.64,\"humidity\":80,\"temp_kf\":0},\"weather\":[{\"id\":500," +
        "\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all" +
        "\":88},\"wind\":{\"speed\":6.33,\"deg\":240.001},\"rain\":{\"3h\":0.33},\"sys\":{\"pod\":" +
        "\"n\"},\"dt_txt\":\"2017-03-01 12:00:00\"},{\"dt\":1488380400,\"main\":{\"temp\":283.423," +
        "\"temp_min\":283.423,\"temp_max\":283.423,\"pressure\":988.22,\"sea_level\":1011.85," +
        "\"grnd_level\":988.22,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":" +
        "\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":44}," +
        "\"wind\":{\"speed\":4.74,\"deg\":243.5},\"rain\":{\"3h\":0.025},\"sys\":{\"pod\":\"d" +
        "\"},\"dt_txt\":\"2017-03-01 15:00:00\"},{\"dt\":1488391200,\"main\":{\"temp\":282.862," +
        "\"temp_min\":282.862,\"temp_max\":282.862,\"pressure\":988,\"sea_level\":1011.63," +
        "\"grnd_level\":988,\"humidity\":84,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main" +
        "\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":76}," +
        "\"wind\":{\"speed\":6.01,\"deg\":241.5},\"rain\":{\"3h\":0.525},\"sys\":{\"pod\":\"d" +
        "\"},\"dt_txt\":\"2017-03-01 18:00:00\"},{\"dt\":1488402000,\"main\":{\"temp\":281.752," +
        "\"temp_min\":281.752,\"temp_max\":281.752,\"pressure\":988.55,\"sea_level\":1012.35," +
        "\"grnd_level\":988.55,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main" +
        "\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":20}," +
        "\"wind\":{\"speed\":7.42,\"deg\":252.514},\"rain\":{\"3h\":0.020000000000001},\"sys" +
        "\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-01 21:00:00\"},{\"dt\":1488412800,\"main\":{" +
        "\"temp\":277.603,\"temp_min\":277.603,\"temp_max\":277.603,\"pressure\":991.42," +
        "\"sea_level\":1015.6,\"grnd_level\":991.42,\"humidity\":81,\"temp_kf\":0},\"weather" +
        "\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}]," +
        "\"clouds\":{\"all\":56},\"wind\":{\"speed\":8.11,\"deg\":266.504},\"rain\":{\"3h" +
        "\":0.025},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-02 00:00:00\"},{\"dt" +
        "\":1488423600,\"main\":{\"temp\":273.179,\"temp_min\":273.179,\"temp_max\":273.179," +
        "\"pressure\":996.3,\"sea_level\":1020.85,\"grnd_level\":996.3,\"humidity\":91," +
        "\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":" +
        "\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed" +
        "\":7.62,\"deg\":292.5},\"rain\":{\"3h\":0.065},\"snow\":{\"3h\":0.0375},\"sys" +
        "\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-02 03:00:00\"},{\"dt\":1488434400,\"main" +
        "\":{\"temp\":268.676,\"temp_min\":268.676,\"temp_max\":268.676,\"pressure" +
        "\":1001.45,\"sea_level\":1026.42,\"grnd_level\":1001.45,\"humidity\":88," +
        "\"temp_kf\":0},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":" +
        "\"light snow\",\"icon\":\"13n\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed" +
        "\":7.97,\"deg\":307.504},\"rain\":{},\"snow\":{\"3h\":0.055},\"sys\":{\"pod\":" +
        "\"n\"},\"dt_txt\":\"2017-03-02 06:00:00\"},{\"dt\":1488445200,\"main\":{\"temp" +
        "\":267.909,\"temp_min\":267.909,\"temp_max\":267.909,\"pressure\":1004.79,\"sea_level" +
        "\":1029.91,\"grnd_level\":1004.79,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id" +
        "\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}],\"clouds" +
        "\":{\"all\":12},\"wind\":{\"speed\":6.01,\"deg\":315.006},\"rain\":{},\"snow\":{\"3h" +
        "\":0.0325},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-02 09:00:00\"},{\"dt" +
        "\":1488456000,\"main\":{\"temp\":266.905,\"temp_min\":266.905,\"temp_max\":266.905," +
        "\"pressure\":1007.08,\"sea_level\":1032.48,\"grnd_level\":1007.08,\"humidity\":84," +
        "\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds" +
        "\",\"icon\":\"02n\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":4.09,\"deg\":314.006}," +
        "\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-02 12:00:00\"},{" +
        "\"dt\":1488466800,\"main\":{\"temp\":268.723,\"temp_min\":268.723,\"temp_max\":268.723," +
        "\"pressure\":1009.38,\"sea_level\":1034.65,\"grnd_level\":1009.38,\"humidity\":95," +
        "\"temp_kf\":0},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow" +
        "\",\"icon\":\"13d\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":3.97,\"deg\":315.001}," +
        "\"rain\":{},\"snow\":{\"3h\":0.045},\"sys\":{\"pod\":\"d\"},\"dt_txt\":" +
        "\"2017-03-02 15:00:00\"},{\"dt\":1488477600,\"main\":{\"temp\":270.67,\"temp_min" +
        "\":270.67,\"temp_max\":270.67,\"pressure\":1009.91,\"sea_level\":1035.06,\"grnd_level" +
        "\":1009.91,\"humidity\":92,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear" +
        "\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":56},\"wind\":{" +
        "\"speed\":4.43,\"deg\":313.504},\"rain\":{},\"snow\":{\"3h\":0.03},\"sys\":{\"pod\":\"d" +
        "\"},\"dt_txt\":\"2017-03-02 18:00:00\"},{\"dt\":1488488400,\"main\":{\"temp\":271.734," +
        "\"temp_min\":271.734,\"temp_max\":271.734,\"pressure\":1010.24,\"sea_level\":1035.35," +
        "\"grnd_level\":1010.24,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main" +
        "\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"clouds\":{\"all\":8}," +
        "\"wind\":{\"speed\":4.31,\"deg\":316.003},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"d" +
        "\"},\"dt_txt\":\"2017-03-02 21:00:00\"},{\"dt\":1488499200,\"main\":{\"temp\":268.937," +
        "\"temp_min\":268.937,\"temp_max\":268.937,\"pressure\":1011.69,\"sea_level\":1037.03," +
        "\"grnd_level\":1011.69,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main" +
        "\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0}," +
        "\"wind\":{\"speed\":2.84,\"deg\":324.505},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n" +
        "\"},\"dt_txt\":\"2017-03-03 00:00:00\"},{\"dt\":1488510000,\"main\":{\"temp\":266.351," +
        "\"temp_min\":266.351,\"temp_max\":266.351,\"pressure\":1012.61,\"sea_level\":1038.02," +
        "\"grnd_level\":1012.61,\"humidity\":88,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":" +
        "\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all" +
        "\":32},\"wind\":{\"speed\":1.36,\"deg\":24.0034},\"rain\":{},\"snow\":{},\"sys\":{\"pod" +
        "\":\"n\"},\"dt_txt\":\"2017-03-03 03:00:00\"},{\"dt\":1488520800,\"main\":{\"temp" +
        "\":267.658,\"temp_min\":267.658,\"temp_max\":267.658,\"pressure\":1012.69,\"sea_level" +
        "\":1038.28,\"grnd_level\":1012.69,\"humidity\":92,\"temp_kf\":0},\"weather\":[{\"id" +
        "\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}],\"clouds" +
        "\":{\"all\":48},\"wind\":{\"speed\":1.23,\"deg\":215.004},\"rain\":{},\"snow\":{\"3h" +
        "\":0.24},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-03 06:00:00\"},{\"dt" +
        "\":1488531600,\"main\":{\"temp\":266.025,\"temp_min\":266.025,\"temp_max\":266.025," +
        "\"pressure\":1012.53,\"sea_level\":1038.25,\"grnd_level\":1012.53,\"humidity\":95," +
        "\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky" +
        "\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.02,\"deg\":273.002}," +
        "\"rain\":{},\"snow\":{\"3h\":0.005},\"sys\":{\"pod\":\"n\"},\"dt_txt\":" +
        "\"2017-03-03 09:00:00\"},{\"dt\":1488542400,\"main\":{\"temp\":266.498,\"temp_min" +
        "\":266.498,\"temp_max\":266.498,\"pressure\":1014.03,\"sea_level\":1039.75,\"grnd_level" +
        "\":1014.03,\"humidity\":87,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\"," +
        "\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":20},\"wind\":{" +
        "\"speed\":4.36,\"deg\":267.005},\"rain\":{},\"snow\":{\"3h\":0.01},\"sys\":{\"pod\":" +
        "\"n\"},\"dt_txt\":\"2017-03-03 12:00:00\"},{\"dt\":1488553200,\"main\":{\"temp" +
        "\":270.004,\"temp_min\":270.004,\"temp_max\":270.004,\"pressure\":1016.49," +
        "\"sea_level\":1041.99,\"grnd_level\":1016.49,\"humidity\":94,\"temp_kf\":0},\"weather" +
        "\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}]," +
        "\"clouds\":{\"all\":0},\"wind\":{\"speed\":4.05,\"deg\":300.501},\"rain\":{},\"snow" +
        "\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-03 15:00:00\"},{\"dt\":1488564000," +
        "\"main\":{\"temp\":271.428,\"temp_min\":271.428,\"temp_max\":271.428,\"pressure" +
        "\":1017.94,\"sea_level\":1043.27,\"grnd_level\":1017.94,\"humidity\":85,\"temp_kf" +
        "\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds" +
        "\",\"icon\":\"03d\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":6.81,\"deg\":299.001}," +
        "\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-03 18:00:00\"},{" +
        "\"dt\":1488574800,\"main\":{\"temp\":271.898,\"temp_min\":271.898,\"temp_max" +
        "\":271.898,\"pressure\":1017.8,\"sea_level\":1043.12,\"grnd_level\":1017.8,\"humidity" +
        "\":76,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":" +
        "\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":6.47,\"deg" +
        "\":303.005},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":" +
        "\"2017-03-03 21:00:00\"},{\"dt\":1488585600,\"main\":{\"temp\":269.795,\"temp_min" +
        "\":269.795,\"temp_max\":269.795,\"pressure\":1019.43,\"sea_level\":1044.9,\"grnd_level" +
        "\":1019.43,\"humidity\":68,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear" +
        "\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{" +
        "\"speed\":5.07,\"deg\":298.5},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":" +
        "\"2017-03-04 00:00:00\"},{\"dt\":1488596400,\"main\":{\"temp\":267.824,\"temp_min" +
        "\":267.824,\"temp_max\":267.824,\"pressure\":1021.05,\"sea_level\":1046.76,\"grnd_level" +
        "\":1021.05,\"humidity\":72,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear" +
        "\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{" +
        "\"speed\":4.32,\"deg\":318.502},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt" +
        "\":\"2017-03-04 03:00:00\"},{\"dt\":1488607200,\"main\":{\"temp\":265.626,\"temp_min" +
        "\":265.626,\"temp_max\":265.626,\"pressure\":1023.36,\"sea_level\":1049.24,\"grnd_level" +
        "\":1023.36,\"humidity\":80,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\"," +
        "\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":32},\"wind" +
        "\":{\"speed\":3.85,\"deg\":338},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt" +
        "\":\"2017-03-04 06:00:00\"},{\"dt\":1488618000,\"main\":{\"temp\":265.167,\"temp_min" +
        "\":265.167,\"temp_max\":265.167,\"pressure\":1024.27,\"sea_level\":1050.2,\"grnd_level" +
        "\":1024.27,\"humidity\":83,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\"," +
        "\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":68},\"wind" +
        "\":{\"speed\":3.12,\"deg\":350.502},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"n\"}," +
        "\"dt_txt\":\"2017-03-04 09:00:00\"},{\"dt\":1488628800,\"main\":{\"temp\":264.928," +
        "\"temp_min\":264.928,\"temp_max\":264.928,\"pressure\":1025.57,\"sea_level\":1051.68," +
        "\"grnd_level\":1025.57,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main" +
        "\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all" +
        "\":48},\"wind\":{\"speed\":2.41,\"deg\":2.01077},\"rain\":{},\"snow\":{},\"sys\":{\"pod" +
        "\":\"n\"},\"dt_txt\":\"2017-03-04 12:00:00\"},{\"dt\":1488639600,\"main\":{\"temp" +
        "\":268.794,\"temp_min\":268.794,\"temp_max\":268.794,\"pressure\":1026.81,\"sea_level" +
        "\":1052.74,\"grnd_level\":1026.81,\"humidity\":93,\"temp_kf\":0},\"weather\":[{\"id" +
        "\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}]," +
        "\"clouds\":{\"all\":32},\"wind\":{\"speed\":2.67,\"deg\":51.5001},\"rain\":{},\"snow" +
        "\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-04 15:00:00\"},{\"dt\":1488650400," +
        "\"main\":{\"temp\":270.409,\"temp_min\":270.409,\"temp_max\":270.409,\"pressure" +
        "\":1026.19,\"sea_level\":1051.71,\"grnd_level\":1026.19,\"humidity\":92,\"temp_kf" +
        "\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\"," +
        "\"icon\":\"02d\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":4.31,\"deg\":119.5}," +
        "\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-04 18:00:00\"},{" +
        "\"dt\":1488661200,\"main\":{\"temp\":271.183,\"temp_min\":271.183,\"temp_max\":271.183," +
        "\"pressure\":1023.82,\"sea_level\":1049.1,\"grnd_level\":1023.82,\"humidity\":84," +
        "\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":" +
        "\"scattered clouds\",\"icon\":\"03d\"}],\"clouds\":{\"all\":44},\"wind\":{\"speed" +
        "\":4.46,\"deg\":125.002},\"rain\":{},\"snow\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":" +
        "\"2017-03-04 21:00:00\"},{\"dt\":1488672000,\"main\":{\"temp\":269.448,\"temp_min" +
        "\":269.448,\"temp_max\":269.448,\"pressure\":1023.37,\"sea_level\":1048.78,\"grnd_level" +
        "\":1023.37,\"humidity\":74,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\"," +
        "\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":64},\"wind\":{" +
        "\"speed\":4.56,\"deg\":125.507},\"rain\":{},\"snow\":{\"3h\":0.005},\"sys\":{\"pod\":" +
        "\"n\"},\"dt_txt\":\"2017-03-05 00:00:00\"},{\"dt\":1488682800,\"main\":{\"temp" +
        "\":268.791,\"temp_min\":268.791,\"temp_max\":268.791,\"pressure\":1022.09,\"sea_level" +
        "\":1047.59,\"grnd_level\":1022.09,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id" +
        "\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds" +
        "\":{\"all\":24},\"wind\":{\"speed\":4.87,\"deg\":118.002},\"rain\":{},\"snow\":{},\"sys" +
        "\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-05 03:00:00\"},{\"dt\":1488693600,\"main\":{" +
        "\"temp\":269.777,\"temp_min\":269.777,\"temp_max\":269.777,\"pressure\":1019.64," +
        "\"sea_level\":1045.04,\"grnd_level\":1019.64,\"humidity\":75,\"temp_kf\":0},\"weather" +
        "\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n" +
        "\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":5.22,\"deg\":118.505},\"rain\":{}," +
        "\"snow\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-03-05 06:00:00\"},{\"dt" +
        "\":1488704400,\"main\":{\"temp\":271.605,\"temp_min\":271.605,\"temp_max\":271.605," +
        "\"pressure\":1017.24,\"sea_level\":1042.52,\"grnd_level\":1017.24,\"humidity\":75," +
        "\"temp_kf\":0},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow" +
        "\",\"icon\":\"13n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":5.12,\"deg\":140.007}," +
        "\"rain\":{},\"snow\":{\"3h\":0.08},\"sys\":{\"pod\":\"n\"},\"dt_txt\":" +
        "\"2017-03-05 09:00:00\"},{\"dt\":1488715200,\"main\":{\"temp\":272.422,\"temp_min" +
        "\":272.422,\"temp_max\":272.422,\"pressure\":1014.35,\"sea_level\":1039.53,\"grnd_level" +
        "\":1014.35,\"humidity\":87,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\"," +
        "\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":92},\"wind\":{" +
        "\"speed\":5.02,\"deg\":147.013},\"rain\":{\"3h\":0.02},\"snow\":{\"3h\":0.36},\"sys" +
        "\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-05 12:00:00\"},{\"dt\":1488726000,\"main\":{" +
        "\"temp\":275.999,\"temp_min\":275.999,\"temp_max\":275.999,\"pressure\":1011.82," +
        "\"sea_level\":1036.67,\"grnd_level\":1011.82,\"humidity\":90,\"temp_kf\":0},\"weather" +
        "\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}]," +
        "\"clouds\":{\"all\":92},\"wind\":{\"speed\":5.21,\"deg\":171.504},\"rain\":{\"3h\":0.45}," +
        "\"snow\":{\"3h\":0.025},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-03-05 15:00:00\"}]," +
        "\"city\":{\"id\":5009586,\"name\":\"Shelby\",\"coord\":{\"lat\":42.6709,\"lon" +
        "\":-83.033},\"country\":\"US\",\"population\":74099}}";
        Weather[] weather = controller.getWeeklyWeather(data);
        assertEquals("Shelby", weather[0].location.getCity());
        assertEquals("US", weather[0].location.getCountry());
        assertEquals(5, Math.round((weather[0].temperature.getTemp() - 273.15)));
        assertEquals(93.0, weather[0].currentCondition.getHumidity(), DELTA);
        assertEquals(1017.0, weather[0].currentCondition.getPressure(), DELTA);
        assertEquals(2.59, weather[0].wind.getSpeed(), DELTA);
        assertEquals(130.0, weather[0].wind.getDeg(), DELTA);
    }

}
