package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Model.WeatherConditionCheck;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherConditionCheckUnitTest {
    @Test
    public void testIsWeatherBad() {
        WeatherConditionCheck weatherConditionCheck = new WeatherConditionCheck();
        String test1 = "Clear";
        String test2 = "Snow";
        assertEquals(weatherConditionCheck.isWeatherBad(test1), false);
        assertEquals(weatherConditionCheck.isWeatherBad(test2), true);
    }
}
