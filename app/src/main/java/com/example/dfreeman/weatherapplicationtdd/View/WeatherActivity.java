package com.example.dfreeman.weatherapplicationtdd.View;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

        String longText = "Warning: Bad weather in your selected area!";
        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Notification n  = new Notification.Builder(this)
                .setContentTitle("BAD WEATHER WARNING!!")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle().bigText(longText))
                .addAction(R.drawable.icon, "More", pIntent).build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);

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
