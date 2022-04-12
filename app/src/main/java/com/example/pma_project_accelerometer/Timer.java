package com.example.pma_project_accelerometer;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Timer extends CountDownTimer {

    private static final long START_TIME_IN_MILLIS = 30000;
    private long mTimeLeftInMillis;
    private boolean mTimerRunning;

    public void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
    }

    public Timer() {
        super(30000,1000);

        mTimeLeftInMillis = START_TIME_IN_MILLIS;
    }

    public long getmTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }

    public void setmTimeLeftInMillis(long mTimeLeftInMillis) {
        this.mTimeLeftInMillis = mTimeLeftInMillis;
    }

    public boolean ismTimerRunning() {
        return mTimerRunning;
    }

    public void setmTimerRunning(boolean mTimerRunning) {
        this.mTimerRunning = mTimerRunning;
    }

    public void startTimer(long millisUntilFinished){
        onTick(millisUntilFinished);
        onFinish();
        start();
    }


    @Override
    public void onTick(long millisUntilFinished) {
        setmTimeLeftInMillis(millisUntilFinished);

        int seconds = (int) getmTimeLeftInMillis()/1000;
        MainActivity.timerText.setText(String.valueOf(seconds));
    }
    @Override
    public void onFinish() {
        setmTimerRunning(false);
        LeaderboardActivity.addToResultsList(MainActivity.stepCount);
        MainActivity.stepCount = 0;
        //Toast.makeText(MainActivity.this,"Your score has been uploaded to the leaderboards",Toast.LENGTH_SHORT).show();
        resetTimer();
    }




}
