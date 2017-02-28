package com.example.dfreeman.weatherapplicationtdd.View;

import android.content.Context;
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
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new Controller();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView textView = (TextView)findViewById(R.id.currentLocation);
        textView.setText(controller.getLocation(this));
        controller.setCachedLocation(controller.getLocation(this));
        this.initialisePaging();
        context = this;

    }

    public void onClickChangeLocation(View v) {
        controller.setLocation(this, "noLocation");
        Intent intent = new Intent(WeatherActivity.this, LocationActivity.class);
        startActivity(intent);
    }

    private void initialisePaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, DailyWeatherFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, WeeklyWeatherFragment.class.getName()));
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }

    public Context getContext() {
        return context;
    }
}
