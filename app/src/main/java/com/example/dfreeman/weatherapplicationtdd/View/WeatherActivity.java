package com.example.dfreeman.weatherapplicationtdd.View;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.R;

import java.util.List;
import java.util.Vector;

public class WeatherActivity extends AppCompatActivity {

    public Controller controller;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new Controller(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView textView = (TextView)findViewById(R.id.currentLocation);
        textView.setText(controller.getLocation());
        this.initialisePaging();

    }

    public void onClickChangeLocation(View v) {
        controller.setLocation("noLocation");
        Intent intent = new Intent(WeatherActivity.this, LocationActivity.class);
        startActivity(intent);
    }

    private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, DailyWeatherFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, DailyWeatherFragment.class.getName()));
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }
}
