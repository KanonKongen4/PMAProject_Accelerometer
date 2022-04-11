package com.example.pma_project_accelerometer;

import android.widget.Button;
import android.widget.TextView;

public class Timer {
    private int timerDuration;
    private boolean isTimerRunning = false;

    //UI TEXT + BUTTONS
    private TextView timerText;
    private Button timerStartButton;

    public Timer(int timerDuration){
        this.timerDuration = timerDuration;

     //   timerText = findViewById(R.id.timerText);

    }
}
