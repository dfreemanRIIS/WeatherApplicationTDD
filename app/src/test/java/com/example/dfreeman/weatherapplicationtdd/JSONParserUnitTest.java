package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Model.JSONParser;
import com.example.dfreeman.weatherapplicationtdd.Model.Weather;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class JSONParserUnitTest {

    private static final double DELTA = 1e-2;

    @Test
    public void testGetWeather() throws JSONException {
        String data = "{\"coord\":{\"lon\":-83.03,\"lat\":42.67},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"},{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"},{\"id\":300,\"main\":\"Drizzle\",\"description\":\"light intensity drizzle\",\"icon\":\"09d\"}],\"base\":\"stations\",\"main\":{\"temp\":278.15,\"pressure\":1017,\"humidity\":93,\"temp_min\":277.15,\"temp_max\":279.15},\"visibility\":4828,\"wind\":{\"speed\":2.6,\"deg\":130},\"clouds\":{\"all\":90},\"dt\":1488291480,\"sys\":{\"type\":1,\"id\":1460,\"message\":0.0052,\"country\":\"US\",\"sunrise\":1488283677,\"sunset\":1488324095},\"id\":5009586,\"name\":\"Shelby\",\"cod\":200}";
        Weather weather = JSONParser.getWeather(data);
        assertEquals("Shelby", weather.location.getCity());
        assertEquals("US", weather.location.getCountry());
        assertEquals(5, Math.round((weather.temperature.getTemp() - 273.15)));
        assertEquals(93.0, weather.currentCondition.getHumidity(), DELTA);
        assertEquals(1017.0, weather.currentCondition.getPressure(), DELTA);
        assertEquals(2.59, weather.wind.getSpeed(), DELTA);
        assertEquals(130.0, weather.wind.getDeg(), DELTA);
    }
}
