package com.example.dfreeman.weatherapplicationtdd;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ControllerUnitTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void testLocation() {
        String test;
        controller.setLocation("0");
        test = controller.getLocation();
        assertEquals("0", test);
        controller.setLocation("48315");
        test = controller.getLocation();
        assertEquals("48315", test);
    }

    @Test
    public void testCheckValid() {
        assertEquals(true, controller.isValid("48315"));
        assertEquals(false, controller.isValid("321"));
        assertEquals(false, controller.isValid("abc"));
    }

}
