package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PreferenceHelperUnitTest {

    private PreferenceHelper preferenceHelper;

    @Before
    public void setUp() {
        preferenceHelper = new PreferenceHelper();
    }

    @Test
    public void testLocation() {
        String test;
        preferenceHelper.setLocation("0");
        test = preferenceHelper.getLocation();
        assertEquals("0", test);
        preferenceHelper.setLocation("48315");
        test = preferenceHelper.getLocation();
        assertEquals("48315", test);
    }

    @Test
    public void testCheckValid() {
        assertEquals(true, preferenceHelper.isValid("48315"));
        assertEquals(false, preferenceHelper.isValid("321"));
        assertEquals(false, preferenceHelper.isValid("abc"));
    }

}
