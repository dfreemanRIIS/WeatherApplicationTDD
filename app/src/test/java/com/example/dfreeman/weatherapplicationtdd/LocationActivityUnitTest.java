package com.example.dfreeman.weatherapplicationtdd;

import android.widget.Button;
import android.widget.EditText;

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
public class LocationActivityUnitTest {

    private LocationActivity locationActivity;
    private Button goButton;
    private EditText editText;

    @Before
    public void setUp() {
        locationActivity = Robolectric.buildActivity(LocationActivity.class).create().get();
        goButton = (Button) locationActivity.findViewById(R.id.enterLocationGoButton);
        editText = (EditText)locationActivity.findViewById(R.id.enterLocationEditText);
    }

    @Test
    public void clickTest() {
        editText.setText("bad input example");
        goButton.callOnClick();
        Assert.assertEquals(null, locationActivity.controller.getLocation(locationActivity.getContext()));
        editText.setText("48315");
        goButton.callOnClick();
        Assert.assertNotSame(editText.getText(), locationActivity.controller.getLocation(locationActivity.getContext()));
    }

    @Test
    public void testLocation() {
        String test;
        locationActivity.controller.setLocation(locationActivity.getContext(), "0");
        test = locationActivity.controller.getLocation(locationActivity.getContext());
        Assert.assertEquals("0", test);
        locationActivity.controller.setLocation(locationActivity.getContext(),"48315");
        test = locationActivity.controller.getLocation(locationActivity.getContext());
        Assert.assertEquals("48315", test);
    }

    @Test
    public void testCheckValid() {
        Assert.assertEquals(true, locationActivity.controller.isValid("48315"));
        Assert.assertEquals(false, locationActivity.controller.isValid("321"));
        Assert.assertEquals(false, locationActivity.controller.isValid("abc"));
    }

}
