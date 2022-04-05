package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AcceleratorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private TextView xAcceleration, yAcceleration, zAcceleration, tiltView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerator);
        xAcceleration = (TextView) findViewById(R.id.xAcceleration);
        yAcceleration = (TextView) findViewById(R.id.yAcceleration);
        zAcceleration = (TextView) findViewById(R.id.zAcceleration);
        tiltView = (TextView) findViewById(R.id.tiltView);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
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

        xAcceleration.setText("x: " + Float.toString(xValue));
        yAcceleration.setText("y: " + Float.toString(yValue));
        zAcceleration.setText("z: " + Float.toString(zValue));
        onTiltPhone(event.values[0],event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onTiltPhone(float x, float y) {
        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) {
                tiltView.setText("Vänster");
                tiltView.setBackgroundColor(Color.GRAY);
            }
            if (x > 0) {
                tiltView.setText("Höger");
                tiltView.setBackgroundColor(Color.YELLOW);
            }
        } else {
            if (y < 0) {
                tiltView.setText("Upp");
                tiltView.setBackgroundColor(Color.GREEN);
            }
            if (y > 0) {
                tiltView.setText("Ned");
                tiltView.setBackgroundColor(Color.CYAN);
            }
        }
        if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
            tiltView.setText("Ej lutad");
        }
    }
}