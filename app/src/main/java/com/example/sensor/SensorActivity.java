package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    // device sensor manager
    private SensorManager SensorManage;
    // define the compass picture that will be use
    private ImageView compassimage;
    // record the angle turned of the compass picture
    private float DegreeStart = 0f;
    private Button audio_button;
    MediaPlayer music;
    TextView DegreeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        //
        compassimage = (ImageView) findViewById(R.id.compass_image);
        // TextView that will display the degree
        DegreeTV = (TextView) findViewById(R.id.DegreeTV);
        // initialize your android device sensor capabilities
        SensorManage = (SensorManager) getSystemService(SENSOR_SERVICE);
        music = (MediaPlayer) MediaPlayer.create(SensorActivity.this, R.raw.a3);
        audio_button = (Button) findViewById(R.id.audio_button);
        audio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.start();
            }
        });
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
        float degree = (float) Math.toDegrees(event.values[0]);
        DegreeTV.setText("Heading: " + Float.toString(degree) + " degrees");
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
        //tones(degree);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
    public void tones(float degree) {
        switch ((int) degree) {
            case 10:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a3);
                music.start();
                break;
                /*
            case 20:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b3);
                music.start();
                break;
            case 30:
                music = MediaPlayer.create(SensorActivity.this, R.raw.c3);
                music.start();
                break;
            case 40:
                music = MediaPlayer.create(SensorActivity.this, R.raw.d3);
                music.start();
                break;
            case 50:
                music = MediaPlayer.create(SensorActivity.this, R.raw.e3);
                music.start();
                break;
            case 60:
                music = MediaPlayer.create(SensorActivity.this, R.raw.f3);
                music.start();
                break;
            case 70:
                music = MediaPlayer.create(SensorActivity.this, R.raw.g3);
                music.start();
                break;
            case 80:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a4);
                music.start();
                break;
            case 90:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b4);
                music.start();
            case 100:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a3);
                music.start();
                break;
            case 110:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b3);
                music.start();
                break;
            case 120:
                music = MediaPlayer.create(SensorActivity.this, R.raw.c3);
                music.start();
                break;
            case 130:
                music = MediaPlayer.create(SensorActivity.this, R.raw.d3);
                music.start();
                break;
            case 140:
                music = MediaPlayer.create(SensorActivity.this, R.raw.e3);
                music.start();
                break;
            case 150:
                music = MediaPlayer.create(SensorActivity.this, R.raw.f3);
                music.start();
                break;
            case 160:
                music = MediaPlayer.create(SensorActivity.this, R.raw.g3);
                music.start();
                break;
            case 170:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a4);
                music.start();
                break;
            case 180:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b4);
                music.start();
            case 190:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a3);
                music.start();
                break;
            case 200:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b3);
                music.start();
                break;
            case 210:
                music = MediaPlayer.create(SensorActivity.this, R.raw.c3);
                music.start();
                break;
            case 220:
                music = MediaPlayer.create(SensorActivity.this, R.raw.d3);
                music.start();
                break;
            case 230:
                music = MediaPlayer.create(SensorActivity.this, R.raw.e3);
                music.start();
                break;
            case 240:
                music = MediaPlayer.create(SensorActivity.this, R.raw.f3);
                music.start();
                break;
            case 250:
                music = MediaPlayer.create(SensorActivity.this, R.raw.g3);
                music.start();
                break;
            case 260:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a4);
                music.start();
                break;
            case 270:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b4);
                music.start();
            case 280:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a3);
                music.start();
                break;
            case 290:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b3);
                music.start();
                break;
            case 300:
                music = MediaPlayer.create(SensorActivity.this, R.raw.c3);
                music.start();
                break;
            case 310:
                music = MediaPlayer.create(SensorActivity.this, R.raw.d3);
                music.start();
                break;
            case 320:
                music = MediaPlayer.create(SensorActivity.this, R.raw.e3);
                music.start();
                break;
            case 330:
                music = MediaPlayer.create(SensorActivity.this, R.raw.f3);
                music.start();
                break;
            case 340:
                music = MediaPlayer.create(SensorActivity.this, R.raw.g3);
                music.start();
                break;
            case 350:
                music = MediaPlayer.create(SensorActivity.this, R.raw.a4);
                music.start();
                break;
            case 360:
                music = MediaPlayer.create(SensorActivity.this, R.raw.b4);
                music.start();
                break;*/
            default:
        }
    }
}