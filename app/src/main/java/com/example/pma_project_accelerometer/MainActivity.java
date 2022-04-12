package com.example.pma_project_accelerometer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    //StepCounterVariable
    private SensorManager mSensorManager;
    private Sensor mySteps;
    private TextView stepCountText;
    private Button btn_leaderboard;
    private boolean isCounterSensorPresent;
    public static int stepCount = 0;

    //TIMER UI + variables
    public static TextView timerText;
    private Button timerStartButton;
    Timer timer = new Timer(MainActivity.this);
    private CountDownTimer mCountDownTimer;


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
                if(timer.ismTimerRunning() == false){
                    Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Please wait until the timer is done",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) { //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
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
                if (timer.ismTimerRunning() == false) {
                    timer.startTimer(30000);
                    timer.setmTimerRunning(true);
                    stepCountText.setText("0");
                }
            }
        });
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(timer.ismTimerRunning() == true){
            stepCount++;
            stepCountText.setText(String.valueOf(stepCount));
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

}