package com.example.sensor;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button sensorButton, acceleratorButton;
    private TextView menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.menu);
        menu.setText("Select option");
        sensorButton = findViewById(R.id.sensorActivityButton);
        sensorButton.setText("Compass");
        acceleratorButton = findViewById(R.id.acceleratorActivityButton);
        acceleratorButton.setText("Accelerator");
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity("SensorActivity");
            }
        });
        acceleratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity("AcceleratorActivity");
            }
        });
    }
    public void openNewActivity(String activity){
        if(activity.equals("SensorActivity")) {
            Intent intent = new Intent(this, SensorActivity.class);
            System.out.println(SensorActivity.class);
            startActivity(intent);
        }
        if(activity.equals("AcceleratorActivity")) {
            Intent intent = new Intent(this, AcceleratorActivity.class);
            System.out.println(AcceleratorActivity.class);
            startActivity(intent);
        }
    }
}