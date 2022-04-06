package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AcceleratorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private TextView xAcceleration, yAcceleration, zAcceleration, average,  tiltView;
    private ImageView pressureNeedle;
    private View view;
    private DecimalFormat df;
    private float DegreeStart = 225;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerator);
        view = findViewById(R.id.acceleratorBackground);
        xAcceleration = findViewById(R.id.xAcceleration);
        yAcceleration = findViewById(R.id.yAcceleration);
        zAcceleration = findViewById(R.id.zAcceleration);
        average = findViewById(R.id.average);
        pressureNeedle = findViewById(R.id.pressureNeedle);
        tiltView = findViewById(R.id.tiltView);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        df = new DecimalFormat("##.##");
    }
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                ,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float xValue = Math.abs(event.values[0]);
        float yValue = Math.abs(event.values[1]);
        float zValue = Math.abs(event.values[2]);
        float vector = (float) Math.sqrt(Math.pow(xValue,2)
                + Math.pow(yValue - 9.81,2) + Math.pow(zValue,2));
        float speed = (float) Math.toDegrees(vector * Math.PI / 15);
        xAcceleration.setText("x: " + df.format(xValue));
        yAcceleration.setText("y: " + df.format(yValue));
        zAcceleration.setText("z: " + df.format(zValue));
        average.setText("Vector: " + df.format(speed/10) + " m/s^2");
        RotateAnimation ra = new RotateAnimation(
                225 + speed,
                225,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        // set the compass animation after the end of the reservation status
        // set how long the animation for the compass image will take place
        // Start animation of compass image
        ra.setDuration(400);
        pressureNeedle.startAnimation(ra);
        onTiltPhone(event.values[0],event.values[1]);
        warningLight(vector);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void warningLight(float vector){
        if(vector >= 5 && vector < 10) {
            view.setBackgroundColor(Color.GREEN);
        } else if(vector >= 10 && vector < 15) {
            view.setBackgroundColor(Color.YELLOW);
        } else if(vector >= 15) {
            view.setBackgroundColor(Color.RED);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    public void onTiltPhone(float x, float y) {
        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) {
                tiltView.setText("Left");
            }
            if (x > 0) {
                tiltView.setText("Right");
            }
        } else {
            if (y < 0) {
                tiltView.setText("Up");
            }
            if (y > 0) {
                tiltView.setText("Down");
            }
        }
        if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
            tiltView.setText("Ej lutad");
        }
    }
}