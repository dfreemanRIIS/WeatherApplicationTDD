package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.View.LocationActivity;

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

public class ControllerUnitTest {

    private Controller controller;
    private LocationActivity locationActivity;

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
    public void testCheckValid() {
        assertEquals(true, controller.isValid("48315"));
        assertEquals(false, controller.isValid("321"));
        assertEquals(false, controller.isValid("abc"));
    }

}
