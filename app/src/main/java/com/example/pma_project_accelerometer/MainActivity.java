package com.example.pma_project_accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
<<<<<<< Updated upstream
=======

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

>>>>>>> Stashed changes
}