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
        controller = new Controller();
        String location = controller.getLocation();
        if(location.equals("testLocation")) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);
        } else {
            Intent intent = new Intent(LocationActivity.this, WeatherActivity.class);
            startActivity(intent);
        }
    }

    public void onClickGo(View v) {
        EditText editText = (EditText)findViewById(R.id.enterLocationEditText);
        String enteredText = editText.getText().toString();
        if(controller.isValid(enteredText)) {
            controller.setLocation(enteredText);
            Intent intent = new Intent(LocationActivity.this, WeatherActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Not a valid location", Toast.LENGTH_SHORT).show();
        }
    }
}
