package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;
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

public class PreferenceHelperUnitTest {

    private PreferenceHelper preferenceHelper;
    private LocationActivity locationActivity;

    @Before
    public void setUp() {
        preferenceHelper = new PreferenceHelper();
        locationActivity = Robolectric.buildActivity(LocationActivity.class).create().get();
    }

    @Test
    public void testLocation() {
        String test;
        preferenceHelper.save(locationActivity, "0");
        test = preferenceHelper.getValue(locationActivity);
        assertEquals("0", test);
        preferenceHelper.save(locationActivity, "48315");
        test = preferenceHelper.getValue(locationActivity);
        assertEquals("48315", test);
    }

    @Test
    public void testCheckValid() {
        assertEquals(true, preferenceHelper.isValid("48315"));
        assertEquals(false, preferenceHelper.isValid("321"));
        assertEquals(false, preferenceHelper.isValid("abc"));
    }

}
