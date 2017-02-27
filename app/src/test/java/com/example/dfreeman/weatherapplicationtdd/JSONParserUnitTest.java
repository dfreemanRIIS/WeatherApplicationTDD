package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Model.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class JSONParserUnitTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testTodaysWeather() throws JSONException {
        String location = "45678";
        int month = 1;
        int day = 1;
        JSONObject jo = new JSONObject();
        jo.put("month", "1");
        jo.put("day", "1");
        jo.put("temp", "60");
        JSONParser jsonParser = new JSONParser();
        assertEquals("60", jsonParser.todaysWeather(location, month, day, jo));

        location = "12345";
        month = 2;
        day = 2;
        JSONObject jo2 = new JSONObject();
        jo.put("month", "2");
        jo.put("day", "2");
        jo.put("temp", "70");
        JSONParser jsonParser2 = new JSONParser();
        assertEquals("70", jsonParser2.todaysWeather(location, month, day, jo2));
    }
}
