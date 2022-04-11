package com.example.pma_project_accelerometer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    //StepCounterVariable
    private SensorManager mSensorManager;
    private Sensor mySteps;
    private TextView stepCountText;
    private Button btn_leaderboard;
    private boolean isCounterSensorPresent;
    int stepCount = 0;

    //TIMER UI + variables
    private TextView timerText;
    private Button timerStartButton;

    private static final long START_TIME_IN_MILLIS = 60000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gå til Leaderboards
        btn_leaderboard = findViewById(R.id.btn_leaderboard);
        btn_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) { //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
        //Random linje som indisk mand sagde jeg skulle adde?
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Stepcounter
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySteps = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        //Text
        stepCountText = findViewById(R.id.stepCountText);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mySteps = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            isCounterSensorPresent = true;
        } else {
            stepCountText.setText(("Counter Sensor is not present"));
            isCounterSensorPresent = false;
        }

        //TIMER UI
        timerStartButton = findViewById(R.id.timerStartButton);
        timerText = findViewById(R.id.timerText);

        //TIMER BUTTON ON CLICK
        timerStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mTimerRunning) {
                    startTimer();
                    mTimerRunning = true;
                    stepCountText.setText("0");
                }
            }
        });
        updateCountDownText();

    }

        private void startTimer() {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;

                }

            }.start();

            mTimerRunning = true;
        }



        private void updateCountDownText(){
        int seconds = (int) mTimeLeftInMillis/1000;
        timerText.setText(String.valueOf(seconds));
        }






    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(mTimerRunning == true){
            //stepCount = (int) sensorEvent.values[0];
            stepCountText.setText(String.valueOf(stepCount));
            stepCount++;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null){
            mSensorManager.registerListener(this,mySteps,SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null){
            mSensorManager.unregisterListener(this,mySteps);
        }
    }

}