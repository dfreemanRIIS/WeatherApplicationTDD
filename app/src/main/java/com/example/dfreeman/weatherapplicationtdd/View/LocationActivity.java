package com.example.dfreeman.weatherapplicationtdd.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.R;

public class LocationActivity extends AppCompatActivity {

    public Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        controller = new Controller();
        if(controller.isValid(controller.getLocation(this))) {
            Intent intent = new Intent(LocationActivity.this, WeatherActivity.class);
            startActivity(intent);
        }
    }

    public void onClickGo(View v) {
        EditText editText = (EditText) findViewById(R.id.enterLocationEditText);
        String enteredText = editText.getText().toString();
        if (controller.isValid(enteredText)) {
            controller.setLocation(this, enteredText);
            Intent intent = new Intent(LocationActivity.this, WeatherActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Not a valid Zip Code", Toast.LENGTH_SHORT).show();
        }
    }
}
