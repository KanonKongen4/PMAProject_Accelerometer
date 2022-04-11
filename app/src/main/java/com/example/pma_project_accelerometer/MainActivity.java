package com.example.pma_project_accelerometer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    //StepCounterVariable
    private SensorManager mSensorManager;
    private Sensor mySteps;
    private TextView stepCountText;
    private boolean isCounterSensorPresent;
    int stepCount = 0;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        //Random linje som indisk mand sagde jeg skulle adde?
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Stepcounter
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySteps = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //Text
        stepCountText = findViewById(R.id.stepCountText);

        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null) {
            mySteps = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }
        else {
            stepCountText.setText(("Counter Sensor is not present"));
            isCounterSensorPresent = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == mySteps){
            //stepCount = (int) sensorEvent.values[0];
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
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            mSensorManager.registerListener(this,mySteps,SensorManager.SENSOR_DELAY_FASTEST);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            mSensorManager.unregisterListener(this,mySteps);
        }
    }

}