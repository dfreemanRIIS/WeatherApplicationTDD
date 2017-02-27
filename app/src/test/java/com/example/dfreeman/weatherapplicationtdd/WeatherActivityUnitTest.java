package com.example.dfreeman.weatherapplicationtdd;

import android.widget.Button;

import com.example.dfreeman.weatherapplicationtdd.View.WeatherActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class WeatherActivityUnitTest {

    private WeatherActivity weatherActivity;
    private Button changeLocatinButton;

    @Before
    public void setUp() {
        weatherActivity = Robolectric.buildActivity(WeatherActivity.class).create().get();
        changeLocatinButton = (Button) weatherActivity.findViewById(R.id.enterLocationGoButton);
    }

    @Test
    public void testLocation() {
        String test;
        weatherActivity.controller.setLocation(weatherActivity.getContext(), "0");
        test = weatherActivity.controller.getLocation(weatherActivity.getContext());
        Assert.assertEquals("0", test);
        weatherActivity.controller.setLocation(weatherActivity.getContext(), "48315");
        test = weatherActivity.controller.getLocation(weatherActivity.getContext());
        Assert.assertEquals("48315", test);
    }

}
