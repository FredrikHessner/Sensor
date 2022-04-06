package com.example.sensor;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    // device sensor manager
    private SensorManager SensorManage;
    private View view;
    private DecimalFormat df;
    // define the compass picture that will be use
    private ImageView compassimage;
    // record the angle turned of the compass picture
    private float DegreeStart = 0f;
    MediaPlayer music;
    private String musicLocator;
    TextView DegreeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        view = findViewById(R.id.compassBackground);
        //
        compassimage = findViewById(R.id.compass_image);
        // TextView that will display the degree
        DegreeTV = findViewById(R.id.menu);
        // initialize your android device sensor capabilities
        SensorManage = (SensorManager) getSystemService(SENSOR_SERVICE);
        music = MediaPlayer.create(SensorActivity.this, R.raw.a3);
        df = new DecimalFormat("###.##");
        musicLocator = "";
    }
    @Override
    protected void onPause() {
        super.onPause();
        // to stop the listener and save battery
        SensorManage.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // code for system's orientation sensor registered listeners
        SensorManage.registerListener(this, SensorManage.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // get angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        DegreeTV.setText("Heading: " + df.format(degree) + " degrees");
        // rotation animation - reverse turn degree degrees
        RotateAnimation ra = new RotateAnimation(
                DegreeStart,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        // set the compass animation after the end of the reservation status
        ra.setFillAfter(true);
        // set how long the animation for the compass image will take place
        ra.setDuration(210);
        // Start animation of compass image
        compassimage.startAnimation(ra);
        DegreeStart = -degree;
        tones(degree);

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
    public void tones(float degree) {
        if(degree >= 0 && degree <= 20 && !musicLocator.equals(String.valueOf(R.raw.c3))) {
            changeTone(R.raw.c3);
            view.setBackgroundColor(Color.parseColor("#e3f2fd"));
        }
        else if(degree > 20 && degree <= 40 && !musicLocator.equals(String.valueOf(R.raw.d3))) {
            changeTone(R.raw.d3);
            view.setBackgroundColor(Color.parseColor("#bbdefb"));
        }
        else if(degree > 40 && degree <= 60 && !musicLocator.equals(String.valueOf(R.raw.e3))) {
            changeTone(R.raw.e3);
            view.setBackgroundColor(Color.parseColor("#90caf9"));
        }
        else if(degree > 60 && degree <=  80 && !musicLocator.equals(String.valueOf(R.raw.f3))) {
            changeTone(R.raw.f3);
            view.setBackgroundColor(Color.parseColor("#64b5f6"));
        }
        else if(degree > 80 && degree <= 100 && !musicLocator.equals(String.valueOf(R.raw.g3))) {
            changeTone(R.raw.g3);
            view.setBackgroundColor(Color.parseColor("#42a5f5"));
        }
        else if(degree > 100 && degree <=  120 && !musicLocator.equals(String.valueOf(R.raw.a4))) {
            changeTone(R.raw.a4);
            view.setBackgroundColor(Color.parseColor("#2196f3"));
        }
        else if(degree > 120 && degree <= 140 && !musicLocator.equals(String.valueOf(R.raw.b4))) {
            changeTone(R.raw.b4);
            view.setBackgroundColor(Color.parseColor("#1e88e5"));
        }
        else if(degree > 140 && degree <=  160 && !musicLocator.equals(String.valueOf(R.raw.c4))) {
            changeTone(R.raw.c4);
            view.setBackgroundColor(Color.parseColor("#1976d2"));
        }
        else if(degree > 160 && degree <= 180 && !musicLocator.equals(String.valueOf(R.raw.d4))) {
            changeTone(R.raw.d4);
            view.setBackgroundColor(Color.parseColor("#1565c0"));
        }
        else if(degree > 180 && degree <= 200 && !musicLocator.equals(String.valueOf(R.raw.e4))) {
            changeTone(R.raw.e4);
            view.setBackgroundColor(Color.parseColor("#0d47a1"));
        }
        else if(degree > 200 && degree <= 220 && !musicLocator.equals(String.valueOf(R.raw.f4))) {
            changeTone(R.raw.f4);
            view.setBackgroundColor(Color.parseColor("#10451d"));
        }
        else if(degree > 220 && degree <=  240 && !musicLocator.equals(String.valueOf(R.raw.g4))) {
            changeTone(R.raw.g4);
            view.setBackgroundColor(Color.parseColor("#155d27"));
        }
        else if(degree > 240 && degree <= 260 && !musicLocator.equals(String.valueOf(R.raw.a5))) {
            changeTone(R.raw.a5);
            view.setBackgroundColor(Color.parseColor("#1a7431"));
        }
        else if(degree > 260 && degree <=  280 && !musicLocator.equals(String.valueOf(R.raw.b5))) {
            changeTone(R.raw.b5);
            view.setBackgroundColor(Color.parseColor("#208b3a"));
        }
        else if(degree > 280 && degree <= 300 && !musicLocator.equals(String.valueOf(R.raw.c5))) {
            changeTone(R.raw.c5);
            view.setBackgroundColor(Color.parseColor("#25a244"));
        }
        else if(degree > 300 && degree <= 320 && !musicLocator.equals(String.valueOf(R.raw.d5))) {
            changeTone(R.raw.d5);
            view.setBackgroundColor(Color.parseColor("#2dc653"));
        }
        else if(degree > 320 && degree <= 340 && !musicLocator.equals(String.valueOf(R.raw.e5))) {
            changeTone(R.raw.e5);
            view.setBackgroundColor(Color.parseColor("#4ad66d"));
        }
        else if(degree > 340 && degree <= 359 && !musicLocator.equals(String.valueOf(R.raw.f5))) {
            changeTone(R.raw.f5);
            view.setBackgroundColor(Color.parseColor("#6ede8a"));
        }
    }
    public void changeTone(int id) {
        musicLocator = String.valueOf(id);
        music.release();
        music = MediaPlayer.create(SensorActivity.this, id);
        music.start();
    }
}