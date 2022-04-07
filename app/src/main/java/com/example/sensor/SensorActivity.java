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
import java.util.ArrayList;
import java.util.Arrays;

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
    private int musicLocator;
    Integer[] toneList;
    String[] colorList;
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
        musicLocator = 0;
        toneList = new Integer[]{R.raw.c3,R.raw.d3,R.raw.e3,R.raw.f3,R.raw.d3,R.raw.e3,R.raw.f3,R.raw.g3,
                R.raw.a4,R.raw.b4,R.raw.c4,R.raw.d4,R.raw.e4,R.raw.f4,R.raw.g4,R.raw.a5,R.raw.b5,R.raw.c5,R.raw.d5,
                R.raw.e5,R.raw.f5};
        colorList = new String[]{"#e3f2fd","#bbdefb","#90caf9", "#64b5f6","#42a5f5","#2196f3",
                "#1e88e5","#1976d2","#1565c0", "#0d47a1","#10451d","#155d27",
                "#1a7431","#208b3a","#25a244", "#2dc653","#4ad66d","#6ede8a"};
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
        for(int i = 0; i >= 360; i+=20 ){
            if(degree >= i && degree <= i+20 && musicLocator != i) {
                musicLocator = i;
                changeTone(toneList[i/20]);
                view.setBackgroundColor(Color.parseColor(colorList[i/20]));
            }
        }
    }
    
    public void changeTone(int id) {
        music.release();
        music = MediaPlayer.create(SensorActivity.this, id);
        music.start();
    }
}